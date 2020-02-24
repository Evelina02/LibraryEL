package by.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import by.library.dao.UserDAO;
import by.library.dao.exception.DAOException;
import by.library.dao.factory.DAOFactory;
import by.library.scanner.Hashing;
import by.library.service.exception.UserServiceException;

public class User implements Serializable{

	private static final long serialVersionUID = 3L;
	
	private String name;
	private String login;
	private String hashedPassword;
	private UserType userType;
	private boolean isSignedIn;
	
	public User() {}

	public User(String name, String login, String hashedPassword, UserType userType) 
	{
		this.name = name;
		this.login = login;
		this.userType = userType;
		this.hashedPassword = hashedPassword;
	}
	
	public final String getName(){
		return name;
	}
	
	public final void setName(String name) {
		this.name = name;
	}
	
	public final String getLogin(){
		return login; 
	}
	
	public final void setLogin(String login){ 
		this.login = login; 
	}
		
    public final boolean isSignedIn() {
		return isSignedIn;
	}

	public final void setSignedIn(boolean isSignedIn) {
		this.isSignedIn = isSignedIn;
	}

	public final void setHashedPassword(String password) {
        Hashing hashing = Hashing.getInstance();
        this.hashedPassword = hashing.getHashedPassword(password);
    }

    public final String getHashedPassword() {
        return hashedPassword;
    }
	
    public final UserType getUserType() {
        return userType;
    }
	  
    public void signOut()
    {
    	this.isSignedIn = false;
    }
    // зачем логику в бин добавляешь, мы же не pojo делаем?
	public void register(User user) throws UserServiceException
	{
		try {
			DAOFactory daoFactory = DAOFactory.getInstance();// а еще и в дао лезешь
			// получается, это объект можно передать куда угодно, и откуда угодно дернуть дао - в чем великий сакральный смысл такого тода тогда?
			UserDAO userDAO = daoFactory.getUserDAO();
			
			ArrayList<User> users = userDAO.getAllUsers();
        
			int count = 0;
			for(User u : users)
			{
				if(u.getLogin().equals(user.getLogin()))
					count++;
			}

			if(count == 0) {

				users.add(user);
				userDAO.register(user);
			}
			else 
				throw new UserServiceException("User with such login is already exists!");

		}catch(DAOException e) {
			throw new UserServiceException("Error during registration", e);
		}
	}
	
	// о, мы и статическими методами обрасли
	public static User getUserByLogin(String login) throws UserServiceException
	{
		try {

			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();
			ArrayList<User> users = userDAO.getAllUsers();

			for (User u : users) {
				if(u.getLogin().equals(login))
        			return u;
			}
        
			throw new UserServiceException("There is not such user in system!");
		}catch(DAOException e) {
			throw new UserServiceException("Error during getting a user by login", e);
		}
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashedPassword == null) ? 0 : hashedPassword.hashCode());
		result = prime * result + (isSignedIn ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (hashedPassword == null) {
			if (other.hashedPassword != null)
				return false;
		} else if (!hashedPassword.equals(other.hashedPassword))
			return false;
		if (isSignedIn != other.isSignedIn)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + " [name=" + name + ", "
				+ "login=" + login + ", "
				+ "userType=" + userType + "]";
	}

}
