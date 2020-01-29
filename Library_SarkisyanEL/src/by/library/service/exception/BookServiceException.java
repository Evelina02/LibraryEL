package by.library.service.exception;

public class BookServiceException extends ServiceException {

	public BookServiceException() {
		super();
	}

	public BookServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookServiceException(String message) {
		super(message);
	}

	public BookServiceException(Throwable cause) {
		super(cause);
	}

}
