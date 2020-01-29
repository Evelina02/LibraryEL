package by.library.controller.command.impl;

import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;
import by.library.service.factory.ServiceFactory;

public class SignOut implements Command {

	private final String paramDelimeter = "--";
	
	@Override
	public String execute(String request) throws ControllerException{
		//Формат строки: sign_out--login
		
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
    	String[] wordsOfRequest = request.split(paramDelimeter);
		
		String login = wordsOfRequest[1];
		
		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		
		try {
			clientService.signOut(login);
				
			response = "Good bye";
		}  catch(UserServiceException e) {
			response = "Error during login procedure";
			throw new ControllerException("Error!", e);
		}
		return response;
	}

}
