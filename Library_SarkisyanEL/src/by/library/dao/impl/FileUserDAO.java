package by.library.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import by.library.bean.User;
import by.library.bean.UserType;
import by.library.dao.UserDAO;
import by.library.dao.exception.UserDAOException;

public class FileUserDAO implements UserDAO{

	private final File file = new File("resource/users.txt");
	
	@Override
	public void register(User user) throws UserDAOException
	{
		BufferedWriter bufferedWriter = null;
		 try {
		    	FileWriter fileWriter = new FileWriter(file, true);//поток, который подключается к файлу
		    	bufferedWriter = new BufferedWriter(fileWriter);

	            bufferedWriter.write(user.getName() + "--" + 
	            					user.getLogin()+ "--" + 
    								user.getHashedPassword() + "--" +
	            					user.getUserType() + "\n");

	            
	        } catch(IOException e) {
	        	throw new UserDAOException("Error during writing user information in file!", e);
	        }
	        finally {
	        	try {
	        		bufferedWriter.close();
	          	}catch(IOException ex) {
	        		throw new UserDAOException("Error during closing buffer!", ex);
    	}
	        }
	}
	
	@Override
	public ArrayList<User> getAllUsers() throws UserDAOException {
		 
	    ArrayList<User> users = new ArrayList<>();
	    BufferedReader bufferedReader = null;
	        try {
	          	FileReader fileReader = new FileReader(file);//поток, который подключается к файлу
	          	bufferedReader = new BufferedReader(fileReader);

	        	String line;
	            while ((line = bufferedReader.readLine()) != null) {
	            	
	            	String[] wordsOfLine = line.split("--");

	            	User user = new User(wordsOfLine[0], 
	            			wordsOfLine[1],
	            			wordsOfLine[2],
	            			UserType.valueOf(wordsOfLine[3]));
	            			
	            	users.add(user);

	            }
	        } catch(IOException e) {
	         	throw new UserDAOException("Error during reading user information from file!", e);
	        }
	        finally {
	        	try {
	        		bufferedReader.close();
	          	}catch(IOException ex) {
	        		throw new UserDAOException("Error during closing buffer!", ex);
	          	}
	        }
	        
	        return users;
	    }
}
