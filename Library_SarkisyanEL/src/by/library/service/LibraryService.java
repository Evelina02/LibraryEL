package by.library.service;

import java.util.ArrayList;

import by.library.bean.Book;
import by.library.bean.Library;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.ServiceException;

public interface LibraryService {

		void addNewBook(Book book) throws BookServiceException;
		
		void deleteBook(String title, String author) throws BookServiceException;
		
		void clearLibrary() throws BookServiceException;
		
		ArrayList<Book> searchBookByTitle(String title) throws BookServiceException;
	
		ArrayList<Book> searchBookByAuthor(String author) throws BookServiceException;
		
		ArrayList<Book> searchBookByTitleAndAuthor(String title, String author) throws BookServiceException;

		Library getLibrary();

		void sortByTitle() throws BookServiceException;
		
		void sortByAuthor() throws BookServiceException;
		
		void sortByYear() throws BookServiceException;

}
