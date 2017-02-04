package exceptions;

public class InvalidUsernameException extends InvalidUserDataException {

	public InvalidUsernameException() {
		super("The username is invalid.");
	}
	
	public InvalidUsernameException(final String message) {
		super(message);
	}

	public InvalidUsernameException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidUsernameException(final Throwable cause) {
		super(cause);
	}
}
