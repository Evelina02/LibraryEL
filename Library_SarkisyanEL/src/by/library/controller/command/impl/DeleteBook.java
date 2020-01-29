package by.library.controller.command.impl;

import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.LibraryService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.factory.ServiceFactory;

public class DeleteBook implements Command {

	private final String paramDelimeter = "--";

	@Override
	public String execute(String request) throws ControllerException{
		//Формат строки: delete_book--title--author 
		
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
    	String[] wordsOfRequest = request.split(paramDelimeter);
		
		String title = wordsOfRequest[1];
		String author = wordsOfRequest[2];
		
		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		
		try {
			libraryService.deleteBook(title, author);
			response = "Book was deleted";
				
		}  catch(BookServiceException e) {
			
				response = "Error during login procedure";
				throw new ControllerException("Error!", e);
		}	
		return response;
	}
}
