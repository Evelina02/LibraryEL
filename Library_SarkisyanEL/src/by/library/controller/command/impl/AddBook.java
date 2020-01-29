package by.library.controller.command.impl;

import by.library.bean.Book;
import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.LibraryService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.factory.ServiceFactory;

public class AddBook implements Command {

	private final String paramDelimeter = "--";

	@Override
	public String execute(String request) throws ControllerException {
		//Формат строки: add_book--title--author--year--numberOfPages--info

		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
		String[] wordsOfRequest = request.split(paramDelimeter);
		String title = wordsOfRequest[1];
		String author = wordsOfRequest[2];
		int year = Integer.parseInt(wordsOfRequest[3]);
		int numberOfPages = Integer.parseInt(wordsOfRequest[4]);
		String info = wordsOfRequest[5];

		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		
		try {
			libraryService.addNewBook(new Book(title, author, year, numberOfPages, info));
			response = "The book was added";
			
		}  catch(BookServiceException e) {
			
			response = "Error during adding book";
			throw new ControllerException("Error!", e);
		}
		
		return response;
	}

}
