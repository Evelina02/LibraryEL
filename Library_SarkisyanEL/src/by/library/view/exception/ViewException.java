package by.library.view.exception;

public class ViewException extends Exception{

	public ViewException() {}
	
	public ViewException(String message)
	{
		super(message);
	}

	public ViewException(Throwable cause)
	{
		super(cause);
	}
	
	public ViewException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
