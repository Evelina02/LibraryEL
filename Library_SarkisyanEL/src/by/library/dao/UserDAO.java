package by.library.dao;

import java.util.ArrayList;

import by.library.bean.User;
import by.library.dao.exception.DAOException;
import by.library.dao.exception.UserDAOException;

public interface UserDAO {

	void register(User user) throws UserDAOException;
	ArrayList<User> getAllUsers() throws UserDAOException;

}
