package by.library.service.impl;

import java.util.ArrayList;
import java.util.Scanner;
//тут есть вывод на экран!!!!!!!!!!!!!
import by.library.bean.Book;
import by.library.bean.Library;
import by.library.dao.exception.DAOException;
import by.library.scanner.DataScanner;
import by.library.service.LibraryService;
import by.library.service.exception.BookServiceException;
import by.library.service.exception.UserServiceException;

public class LibraryServiceImpl implements LibraryService{

	private Library library; 
	
	@Override
	public Library getLibrary()
	{
		return library;
	}
	
	@Override
	public void addNewBook(Book book) throws BookServiceException{
		
		if(book == null) 
			throw new BookServiceException("Error during adding book in library: book = null");

		try {
		library = Library.getLibrary();
		library.addBookInLibrary(book);
		
		}catch(DAOException e) {
			throw new BookServiceException("Error during adding book in library", e);
		}
	}

	@Override
	public void deleteBook(String title, String author) throws BookServiceException
	{
		if(title == null || title.isEmpty())
			throw new BookServiceException("Error during deleting book from library : incorrect title");
		
		if(author == null || author.isEmpty())
			throw new BookServiceException("Error during deleting book from library: incorrect author");
		
		try {
			library = Library.getLibrary();
			ArrayList<Book> deleted = searchBookByTitleAndAuthor(title, author);

			for(Book b : deleted)
				library.deleteBookFromLibrary(b);
			
		}catch(DAOException e) {
			throw new BookServiceException("Error during deleting book from library", e);
		}
	}
	
	@Override
	public void clearLibrary() throws BookServiceException
	{
		try {
			library = Library.getLibrary();
			library.clearLibrary();
		}catch(DAOException e) {
			throw new BookServiceException("Error during clearing library", e);
		}
	}

	public ArrayList<Book> searchBookByTitleAndAuthor(String title, String author) throws BookServiceException
	{
		if(title == null || title.isEmpty())
			throw new BookServiceException("Error during searching book from library: incorrect title");
		
		if(author == null || author.isEmpty())
			throw new BookServiceException("Error during searching book from library: incorrect author");
		try {	
			ArrayList<Book> books = new ArrayList<>();
			library = Library.getLibrary();

			int count = 0;
			for (Book book : library.getBooks()) {
				if (title.equalsIgnoreCase(book.getTitle()) && author.equalsIgnoreCase(book.getAuthor())){
					books.add(book);
					count++;
				}
			}
			if (count == 0) {
				throw new BookServiceException("There are not such book in library!");
			}
			return books;

		}catch(DAOException e) {
			throw new BookServiceException("Error during searching book from library", e);
		}
	}
	
	public ArrayList<Book> searchBookByTitle(String title) throws BookServiceException
	{
		if(title == null || title.isEmpty())
			throw new BookServiceException("Incorrect title");
		try {
			ArrayList<Book> books = new ArrayList<>();
			library = Library.getLibrary();

			int count = 0;
			for (Book book : library.getBooks()) {
				if (title.equalsIgnoreCase(book.getTitle())) {
					books.add(book);
					count++;
				}
			}
			if (count == 0) {
	        	throw new BookServiceException("There is not such book in the library!");
			}
			return books;
		}catch(DAOException e) {
			throw new BookServiceException("Error during searching book from library", e);
		}
	}
	
	public ArrayList<Book> searchBookByAuthor(String author)throws BookServiceException
	{
		if(author == null || author.isEmpty())
			throw new BookServiceException("Incorrect author");
		try {

		ArrayList<Book> books = new ArrayList<>();
		library = Library.getLibrary();

        int count = 0;
        for (Book book : library.getBooks()) {
            if (author.equalsIgnoreCase(book.getAuthor())) {
                books.add(book);
                count++;
            }
        }
        if (count == 0) {
        	throw new BookServiceException("There is not such book in the library!");
        }
        return books;
		}catch(DAOException e) {
			throw new BookServiceException("Error during searching book from library", e);
		}
	}
	
	public void sortByTitle() throws BookServiceException
	{
		try {
			library = Library.getLibrary();

			BookTitleComparator bookTitleComparator = new BookTitleComparator();
			library.getBooks().sort(bookTitleComparator);
		}catch(DAOException e) {
			throw new BookServiceException("Error during sorting books by title", e);
		}
	}
	
	public void sortByAuthor() throws BookServiceException
	{
		try {
			library = Library.getLibrary();

			BookAuthorComparator bookAuthorComparator = new BookAuthorComparator();
			library.getBooks().sort(bookAuthorComparator);
		}catch(DAOException e) {
			throw new BookServiceException("Error during sorting books by author", e);
		}
	}
	
	public void sortByYear() throws BookServiceException
	{
		try {
			library = Library.getLibrary();

			BookYearComparator bookYearComparator = new BookYearComparator();
			library.getBooks().sort(bookYearComparator);
		}catch(DAOException e) {
			throw new BookServiceException("Error during sorting books by year", e);
		}
	}
}
