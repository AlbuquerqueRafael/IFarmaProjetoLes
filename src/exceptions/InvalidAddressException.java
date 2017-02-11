package exceptions;

public class InvalidAddressException extends InvalidUserDataException {

	public InvalidAddressException() {
		super("The Address is invalid.");
	}
	
	public InvalidAddressException(final String message) {
		super(message);
	}

	public InvalidAddressException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidAddressException(final Throwable cause) {
		super(cause);
	}
}
