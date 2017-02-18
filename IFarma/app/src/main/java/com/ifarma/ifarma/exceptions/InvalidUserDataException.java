package com.ifarma.ifarma.exceptions;

public class InvalidUserDataException extends Exception{

	public InvalidUserDataException() { super("The user data is invalid.");	}

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
