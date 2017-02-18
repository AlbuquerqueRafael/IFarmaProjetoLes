package com.ifarma.ifarma.exceptions;

public class InvalidCPFException extends InvalidUserDataException {

	public InvalidCPFException() {
		super("The password is invalid.");
	}
	
	public InvalidCPFException(final String message) {
		super(message);
	}

	public InvalidCPFException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidCPFException(final Throwable cause) {
		super(cause);
	}
}
