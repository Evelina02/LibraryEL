package by.library.view;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.bean.User;
import by.library.bean.UserType;
import by.library.scanner.*;
import by.library.service.exception.ServiceException;
import by.library.service.exception.UserServiceException;
import by.library.view.exception.ViewException;

public class Output {

	public static void showBooks(ArrayList<Book> books)
	{
		for(Book book : books)
		{
			System.out.println(book);
		}
	}
	
	public static void showUsers(ArrayList<User> users)
	{
		for(User user : users)
		{
			System.out.println(user);
		}
	}
	
	public static String getTitleFromConsole()
	{
		System.out.println("Write the title of the book:");
		String title = DataScanner.enterStringFromConsole();
      
		return title;
	}
	
	public static String getAuthorFromConsole()
	{
		 System.out.println("Write the author of the book:");
		 String author = DataScanner.enterStringFromConsole();

		 return author;
	}
	
	public static int getYearFromConsole()
	{
		 System.out.println("Write the year of the book:");
		 int year = DataScanner.enterIntFromConsole();
		 
		 return year;
	}
	
	public static int getNumberOfPagesFromConsole()
	{
		 System.out.println("Write number of pages of the book:");
		 int numberOfPages = DataScanner.enterIntFromConsole();
		 
		 return numberOfPages;
	}
	
	public static String getInfoFromConsole()
	{
		 System.out.println("Write the info of the book:");
		 String info = DataScanner.enterStringFromConsole();

		 return info;
	}

	public static int getStartCommand()
	{
		int command = 0;
		do {
		 System.out.println("Please, choose a command and press the number:\n"
		 		+ "1. Sign in \n"
		 		+ "2. Registration \n"
		 		+ "3. Exit \n");
		 command = DataScanner.enterIntFromConsole();
		
		}while(command <1 || command > 3);
		
		return command;
	}
	
	public static String getAuthorizationInfo()
	{
		 System.out.println("Enter your login:\n");
		 String login = DataScanner.enterStringFromConsole();
		 System.out.println("Enter your password:\n");
		 String password = DataScanner.enterStringFromConsole();
		 
		 String response = login + "--" + password;
		 
		 return response;
	}
	
	public static String getInfoOfUser()
	{
		 System.out.println("Enter your name:\n");
		 String name = DataScanner.enterStringFromConsole();
		 System.out.println("Enter your login:\n");
		 String login = DataScanner.enterStringFromConsole();
		 System.out.println("Enter your password:\n");
		 String password1 = DataScanner.enterStringFromConsole();
		 String password2;
		 do {
			 System.out.println("Enter your password once again:\n");
			 password2 = DataScanner.enterStringFromConsole();
		 
		 }while(!password1.equals(password2));
		 
			 Hashing hashing = Hashing.getInstance();
			 String hashedPassword = hashing.getHashedPassword(password1);
			 String response = name + "--" + login + "--" + hashedPassword + "--" + "CLIENT";
			 return response;
		 }
	
	public static int getCommand(String login) throws ViewException
	{
		if(login == null || login.isEmpty())
			throw new ViewException("Incorrect login");
		int command = 0;

		try {
			User user = User.getUserByLogin(login);
			if(user.getUserType() == UserType.CLIENT)
			{
				System.out.println("Please, choose what you want to do and enter the number:\n"
					+ "1. Sign out \n"
					+ "2. Show all books \n"
					+ "3. Search book in library by title \n"
					+ "4. Search book in library by author \n"
					+ "5. Sort books by title and show \n"
					+ "6. Sort books by author and show \n"
					+ "7. Sort books by year and show \n");
			
			command = DataScanner.enterIntFromConsole();
			if(command < 1 || command >7)
				throw new ViewException("Command must be 1-7 !!!");
		}
		else {
			System.out.println("Please, choose what you want to do and enter the number: \n"
					+ "1. Sign out \n"
					+ "2. Show all books \n"
					+ "3. Search book in library by title \n"
					+ "4. Search book in library by author \n"
					+ "5. Sort books by title and show \n"
					+ "6. Sort books by author and show \n"
					+ "7. Sort books by year and show \n"

					+ "8. Add new book in the library \n"
					+ "9. Delete book from the library \n"
					+ "10. Clear the library \n"
					+ "11. Show all users \n");
			
			command = DataScanner.enterIntFromConsole();
			if(command < 1 || command >11)
				throw new ViewException("Command must be 1-11 !!!");
		}
		
	}catch(ServiceException e) {
		throw new ViewException("Error during getting command from console!", e);
	}
		return command;
	}
}
