package by.library.controller.command.impl;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.bean.User;
import by.library.controller.command.Command;
import by.library.controller.exception.ControllerException;
import by.library.service.ClientService;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;
import by.library.service.factory.ServiceFactory;

public class ShowUsers implements Command {

	@Override
	public String execute(String request) throws ControllerException{
		//Формат строки: show_users--
		
		if(request == null || request.isEmpty())
			throw new ControllerException("Error! Incorrect request");
		
		String response = "";

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		
		try {
			ArrayList<User> users = clientService.getAllUsers();
			
			for(User user : users) {
				response = response +  user.toString() + "\n";
			}
		}  catch(UserServiceException e) {
			response = "Error during showing users";
			throw new ControllerException("Error during showing users", e);
		}
		return response;
	}

}
