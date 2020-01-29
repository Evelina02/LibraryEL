package by.library.service;

import java.util.ArrayList;

import by.library.bean.User;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;

public interface ClientService {

	User signIn(String login, String password) throws UserServiceException;
	
	void signOut(String login) throws UserServiceException;
	
	void register(User user) throws UserServiceException;
	
	ArrayList<User> getAllUsers() throws UserServiceException;
}
