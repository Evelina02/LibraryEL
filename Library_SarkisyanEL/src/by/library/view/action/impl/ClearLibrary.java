package by.library.view.action.impl;

import by.library.bean.Library;
import by.library.dao.exception.DAOException;
import by.library.view.action.Action;
import by.library.view.exception.ViewException;

public class ClearLibrary implements Action {

	@Override
	public String doAction() throws ViewException{

		try {
			Library library = Library.getLibrary();

			library.clearLibrary();
			
		}catch(DAOException e) {
			throw new ViewException("Error during clearing library", e);
		}
		return "Library is empty";
	}

}
