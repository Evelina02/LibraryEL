package by.library.controller.command.impl;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.LibraryService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.factory.ServiceFactory;

public class SearchByTitleAndAuthor implements Command{

	private final String paramDelimeter = "--";

	@Override
	public String execute(String request) throws ControllerException{
		//Формат строки:search_by_title_and_author--title--author
		
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
    	String[] wordsOfRequest = request.split(paramDelimeter);
		
		String title = wordsOfRequest[1];
		String author = wordsOfRequest[2];

		String response = "";

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		
		try {
			ArrayList<Book> books = libraryService.searchBookByTitleAndAuthor(title, author);
			
			for(Book b : books) {
				response = response +  b.toString() + "\n";
			}
		}  catch(BookServiceException e) {
			response = "Error during login procedure";
			throw new ControllerException("Error!", e);
		}
		return response;
	}
}
