package by.library.service.impl;

import java.util.Comparator;

import by.library.bean.Book;

public class BookAuthorComparator implements Comparator<Book>{

	
	public int compare(Book book1, Book book2)
	{
		String bookAuthor1 = book1.getAuthor();
		String bookAuthor2 = book2.getAuthor();

		return bookAuthor1.compareTo(bookAuthor2);
	}
}
