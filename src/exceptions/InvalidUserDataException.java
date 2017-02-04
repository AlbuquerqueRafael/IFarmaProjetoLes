package exceptions;

public class InvalidUserDataException extends Exception{
	
	public InvalidUserDataException() {
		super("The name is invalid.");
	}
	
	public InvalidUserDataException(final String message) {
		super(message);
	}

	public InvalidUserDataException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidUserDataException(final Throwable cause) {
		super(cause);
	}
		
}
