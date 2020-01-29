package by.library.controller.command.impl;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.LibraryService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.factory.ServiceFactory;

public class SortByTitleAndShow implements Command {

	public String execute(String request) throws ControllerException{
		//Формат строки: sort_by_title
    	
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
		String response = "";

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		
		try {
			libraryService.sortByTitle();
			ArrayList<Book> books = libraryService.getLibrary().getBooks();
			for(Book b : books) {
				response = response +  b.toString() + "\n";
			}
		}  catch(BookServiceException e) {
			response = "Error during sorting the library by title";
			throw new ControllerException("Error!", e);
		}
		return response;
	}
}
