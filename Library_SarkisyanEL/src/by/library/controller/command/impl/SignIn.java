package by.library.controller.command.impl;

import by.library.bean.User;
import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;
import by.library.service.factory.ServiceFactory;

public class SignIn implements Command {

	private final String paramDelimeter = "--";

	@Override
	public String execute(String request) throws ControllerException{
		//Формат строки: sign_in--login--password
		
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
    	String[] wordsOfRequest = request.split(paramDelimeter);
		
		String login = wordsOfRequest[1];
		String password = wordsOfRequest[2];
		
		String response = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		
		try {
			User checkedUser = clientService.signIn(login, password);
			if(checkedUser != null)
				response = "Hello--" + checkedUser.getName() + "--" + checkedUser.getLogin();
		}  catch(UserServiceException e) {
			response = "Error during login procedure";
			throw new ControllerException("Error!", e);
		}
		return response;
	}

}