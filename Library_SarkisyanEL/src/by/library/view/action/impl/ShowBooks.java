package by.library.view.action.impl;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.bean.Library;
import by.library.dao.exception.DAOException;
import by.library.view.Output;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class ShowBooks implements Action {

	@Override
	public String doAction() throws ViewException{
		try {
			Library library = Library.getLibrary();
			ArrayList<Book> list = library.getBooks();
			Output.showBooks(list);
			
		}catch(DAOException e) {
			throw new ViewException("Error during showing library", e);
		}
		return "Here are all the books in the library";
	}

}
