package by.library.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import by.library.bean.Book;
import by.library.dao.BookDAO;
import by.library.dao.exception.BookDAOException;

public class FileBookDAO implements BookDAO{

	private final File file = new File("resource/library.txt");

	@Override
	public void addBook(Book book) throws BookDAOException
	{
		BufferedWriter bufferedWriter = null;
        try {    
        	FileWriter fileWriter = new FileWriter(file, true);//поток, который подключается к файлу
        	bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(book.getTitle() + "--" + 
            					book.getAuthor() + "--" +
            					book.getYear() + "--" + 
            					book.getNumberOfPages() + "--" + 
            					book.getInfo() + "\n");

        } catch(IOException e) {
        	throw new BookDAOException("Error during writing book information in file!", e);
        }
        finally {
        	try {
        		bufferedWriter.close();
        	}catch(IOException ex) {
            	throw new BookDAOException("Error during closing buffer!", ex);
        	}
        	
        }
   	}

	@Override
	public void overwriteFile(ArrayList<Book> books) throws BookDAOException
	{
		BufferedWriter bufferedWriter = null;
		try {
			FileWriter fileWriter = new FileWriter(file);//поток, который подключается к файлу
	    	bufferedWriter = new BufferedWriter(fileWriter);
	    	
        	for(Book b : books)
        	{
                bufferedWriter.write(b.getTitle() + "--" + 
    					b.getAuthor() + "--" +
    					b.getYear() + "--" + 
    					b.getNumberOfPages() + "--" + 
    					b.getInfo() + "\n");
		    }

		} catch(IOException e) {
        	throw new BookDAOException("Error during overwriting book information in file!", e);
        }
		finally {
			try {
				bufferedWriter.close();        	
			}catch(IOException ex) {
					throw new BookDAOException("Error during closing buffer!", ex);
        	}
		}
	}

	@Override
	public ArrayList<Book> getAllBooks() throws BookDAOException{
		 
		BufferedReader bufferedReader = null;
	    ArrayList<Book> books = new ArrayList<>();

	        try {
	          	FileReader fileReader = new FileReader(file);

	          	bufferedReader = new BufferedReader(fileReader);

	        	String line;
	            while ((line = bufferedReader.readLine()) != null) {
	            	
	            	String[] wordsOfLine = line.split("--");
	            	
	            	Book book = new Book(wordsOfLine[0], 
	            			wordsOfLine[1], 
	            			Integer.parseInt(wordsOfLine[2]), 
	            			Integer.parseInt(wordsOfLine[3]), 
	            			wordsOfLine[4]);
	            			
	            	books.add(book);
	            }
	            
	        } catch(IOException e) {
	        	throw new BookDAOException("Error during reading book information from file!", e);
	        }
	        finally {
	        	try	{
	        		bufferedReader.close();
	        	}catch(IOException ex) {
	        		throw new BookDAOException("Error during closing buffer!", ex);
	        	}
	        }
	        
	        return books;
	}
}
