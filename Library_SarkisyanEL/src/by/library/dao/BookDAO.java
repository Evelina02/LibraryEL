package by.library.dao;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.dao.exception.BookDAOException;
import by.library.dao.exception.DAOException;

public interface BookDAO {

	void addBook(Book book) throws BookDAOException;
	
	void overwriteFile(ArrayList<Book> books) throws BookDAOException;
	
	ArrayList<Book> getAllBooks() throws BookDAOException;

}
