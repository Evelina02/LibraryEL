package by.library.service.impl;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.bean.Library;
import by.library.bean.User;
import by.library.dao.UserDAO;
import by.library.dao.exception.DAOException;
import by.library.dao.factory.DAOFactory;
import by.library.scanner.Hashing;
import by.library.service.ClientService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;

public class ClientServiceImpl implements ClientService{

	@Override
	public User signIn(String login, String password) throws UserServiceException
	{
		if(login == null || login.isEmpty())
			throw new UserServiceException("Error during logging in: incorrect login");
		
		if(password == null || password.isEmpty())
			throw new UserServiceException("Error during logging in: incorrect password");
		
		Hashing hashing = Hashing.getInstance();
        String hashedPassword = hashing.getHashedPassword(password);

		try {

	        DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
	        ArrayList<User> users = userDAO.getAllUsers();
	        
	        for (User user : users) {
	        		String userLogin = user.getLogin();
	        		String userPassword =user.getHashedPassword();
	            if (userLogin.equals(login) && userPassword.equals(hashedPassword)) {
	            	
	            		user.setSignedIn(true); 
	            		return user;
	            }
	        
	        }
	       throw new UserServiceException("Wrong login or password!!");
	        
		}catch(DAOException e) {
			throw new UserServiceException("Error during logging in ", e);
		}
	}
	
	@Override
	public void signOut(String login) throws UserServiceException
	{
		if(login == null || login.isEmpty())
			throw new UserServiceException("Incorrect login");

		User user = User.getUserByLogin(login);
		user.setSignedIn(false);

	}
	
	@Override
	public void register(User user) throws UserServiceException
	{
		if(user == null) 
			throw new UserServiceException("Error during registration: user = null");

			user.register(user);
	}
	
	public ArrayList<User> getAllUsers() throws UserServiceException{
	
		try {
			
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
	        ArrayList<User> users = userDAO.getAllUsers();
			return users;
			
		}catch(DAOException e) {
			throw new UserServiceException("Error during getting users list", e);
		}
	}
}
