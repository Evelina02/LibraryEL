package by.library.bean;

import java.io.Serializable;
import java.util.ArrayList;
import by.library.dao.BookDAO;
import by.library.dao.exception.BookDAOException;
import by.library.dao.exception.DAOException;
import by.library.dao.factory.DAOFactory;

public class Library implements Serializable{
	
	private static final long serialVersionUID = 2L;

	private static Library library;
	private ArrayList<Book> books;
	
	private Library(ArrayList<Book> books) {
		this.books = books;
	}

	public static Library getLibrary() throws BookDAOException{
		
		if(library == null)
		{
			DAOFactory daoFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoFactory.getBookDAO();
			
			ArrayList<Book> allBooks = bookDAO.getAllBooks();
			library = new Library(allBooks);
		}
		return library;
	}

	public final ArrayList<Book> getBooks(){
		return books;
	}
	
	public final void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	
	public void addBookInLibrary(Book book) throws BookDAOException
	{
        if (!books.contains(book)) {
        
            books.add(book);
            DAOFactory daoFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoFactory.getBookDAO();
			bookDAO.addBook(book);
        }
        else 
        	throw new BookDAOException("Such book is already exists!");
	}
	
	public void deleteBookFromLibrary(Book book) throws BookDAOException{
		
		Book deleted = null;
	    if (books.contains(book)) {
	    	for (Book b : books) {
	    		if (book.equals(b)) {
	    			deleted = b;
	            }
	    }
            	
	        books.remove(deleted);
	        DAOFactory daoFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoFactory.getBookDAO();
	        bookDAO.overwriteFile(books);
	            
	    }else {
	    	throw new BookDAOException("There is no such book in the library!");
		}
	}
	
	public void clearLibrary()  throws BookDAOException{
		
		books.clear();
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDAO = daoFactory.getBookDAO();
        bookDAO.overwriteFile(books);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
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
		Library other = (Library) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return getClass().getName() + "@" +" [books=" + books + "]";
	}

}
