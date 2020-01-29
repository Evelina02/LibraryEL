package by.library.controller.command.impl;

import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.LibraryService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.factory.ServiceFactory;

public class ClearLibrary implements Command {

	@Override
	public String execute(String request) throws ControllerException {
		//Формат строки: clear_libary--
    	
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		
		try {
			libraryService.clearLibrary();;
			response = "Library is empty";
		}  catch(BookServiceException e) {
			
			response = "Error during clearing the library";
			throw new ControllerException("Error!", e);
		}
		return response;
	}
}
