package com.ifarma.ifarma.exceptions;

public class InvalidHouseNumberException extends InvalidUserDataException {

	public InvalidHouseNumberException() {
		super("The House Number is invalid.");
	}
	
	public InvalidHouseNumberException(final String message) {
		super(message);
	}

	public InvalidHouseNumberException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidHouseNumberException(final Throwable cause) {
		super(cause);
	}
}
