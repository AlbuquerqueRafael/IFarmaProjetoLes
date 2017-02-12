package exceptions;

public class InvalidCEPException extends InvalidUserDataException {

	public InvalidCEPException() {
		super("The CEP is invalid for Campina Grande.");
	}
	
	public InvalidCEPException(final String message) {
		super(message);
	}

	public InvalidCEPException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidCEPException(final Throwable cause) {
		super(cause);
	}
}
