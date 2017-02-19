package com.ifarma.ifarma.exceptions;

/**
 * Created by gustavooliveira on 2/18/17.
 */
public class SignInException extends Exception{
    public SignInException() {
        super("The user data is invalid.");
    }

    public SignInException(final String message) {
        super(message);
    }

    public SignInException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SignInException(final Throwable cause) {
        super(cause);
    }
}
