package exceptions;

public class InvalidNameException extends InvalidUserDataException {

	public InvalidNameException() {
		super("The name is invalid.");
	}
	
	public InvalidNameException(final String message) {
		super(message);
	}

	public InvalidNameException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidNameException(final Throwable cause) {
		super(cause);
	}
	
}
