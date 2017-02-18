package com.ifarma.ifarma.exceptions;

import com.ifarma.ifarma.exceptions.InvalidUserDataException;

public class InvalidCNPJException extends InvalidUserDataException {

	public InvalidCNPJException() {
		super("The password is invalid.");
	}
	
	public InvalidCNPJException(final String message) {
		super(message);
	}

	public InvalidCNPJException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidCNPJException(final Throwable cause) {
		super(cause);
	}
}
