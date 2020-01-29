package by.library.bean;

import java.io.Serializable;

public class Book implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String author;
	private int year;
	private int numberOfPages;
	private String info;
	
	public Book() {}
	
	public Book(String title, String author, int year, int numberOfPages, String info)
	{
		this.title = title;
		this.author = author;
		this.year = year;
		this.numberOfPages = numberOfPages;
		this.info = info;
	}
	

	public final String getTitle() {
		return title;
	}
	
	public final void setTitle(String title) {
		this.title = title;
	}
	
	public final String getAuthor() {
		return author;
	}
	
	public final void setAuthor(String author) {
		this.author = author;
	}
	
	public final int getYear() {
		return year;
	}
	
	public final void setYear(int year) {
		this.year = year;
	}
	
	public final int getNumberOfPages() {
		return numberOfPages;
	}
	
	public final void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public final String getInfo() {
		return info;
	}
	
	public final void setInfo(String info) {
		this.info = info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + numberOfPages;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (numberOfPages != other.numberOfPages)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + " [title = " + title + ", "
				+ "author = " + author + ", "
				+ "year = " + year + ", "
				+ "numberOfPages = " + numberOfPages
				+ "info = " + info + "]";
	}

}
