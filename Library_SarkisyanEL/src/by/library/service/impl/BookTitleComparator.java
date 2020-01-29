package by.library.service.impl;

import java.util.Comparator;

import by.library.bean.Book;

public class BookTitleComparator implements Comparator<Book>{

		
		public int compare(Book book1, Book book2)
		{
			String bookTitle1 = book1.getTitle();
			String bookTitle2 = book2.getTitle();

			return bookTitle1.compareTo(bookTitle2);
		}
}
