package exceptions;

public class InvalidProductDataException extends InvalidUserDataException {

	public InvalidProductDataException() {
		super("The Product is invalid.");
	}
	
	public InvalidProductDataException(final String message) {
		super(message);
	}

	public InvalidProductDataException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidProductDataException(final Throwable cause) {
		super(cause);
	}
}
