package com.ifarma.ifarma.exceptions;

public class InvalidEmailException extends InvalidUserDataException {

	public InvalidEmailException() {
		super("The Email is invalid.");
	}
	
	public InvalidEmailException(final String message) {
		super(message);
	}

	public InvalidEmailException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidEmailException(final Throwable cause) {
		super(cause);
	}
}
