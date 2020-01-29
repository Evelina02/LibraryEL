package by.library.controller.command.impl;

import by.library.bean.User;
import by.library.bean.UserType;
import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;
import by.library.service.factory.ServiceFactory;

public class Registration implements Command {

	private final String paramDelimeter = "--";

	@Override
	public String execute(String request) throws ControllerException{
		//Формат строки: register--name--login--password--type
    	
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
		String[] wordsOfRequest = request.split(paramDelimeter);
		
		String name = wordsOfRequest[1];
		String login = wordsOfRequest[2];
		String password = wordsOfRequest[3];
		String userType = wordsOfRequest[4];

		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		
		try {
			clientService.register(new User(name, login, password, UserType.valueOf(userType)));;
			//clientService.signIn(login, password);
			response = "Welcome" + "--" + name + "--" + login;
			
		}  catch(UserServiceException e) {
			response = "Error during registration";
			throw new ControllerException("Error!", e);
		}
		return response;
	}
}
