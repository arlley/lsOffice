package com.arlley.pack.exception;

public class BusinessException extends Exception{

    private String message;

    public BusinessException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
