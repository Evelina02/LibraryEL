package by.library.service.impl;

import java.util.Comparator;

import by.library.bean.Book;

public class BookYearComparator implements Comparator<Book>{

	public int compare(Book book1, Book book2)
	{
		int year1 = book1.getYear();
		int year2 = book2.getYear();

		return year1 - year2;
	}
}
