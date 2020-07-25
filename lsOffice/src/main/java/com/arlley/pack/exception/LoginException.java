package com.arlley.pack.exception;

public class LoginException extends Exception {

    private String message;

    public LoginException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public LoginException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
