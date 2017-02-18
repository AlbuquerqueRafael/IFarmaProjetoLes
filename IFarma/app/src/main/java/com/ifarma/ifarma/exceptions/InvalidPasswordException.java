package com.ifarma.ifarma.exceptions;

public class InvalidPasswordException extends InvalidUserDataException {

	public InvalidPasswordException() {
		super("The password is invalid.");
	}
	
	public InvalidPasswordException(final String message) {
		super(message);
	}

	public InvalidPasswordException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidPasswordException(final Throwable cause) {
		super(cause);
	}
}
