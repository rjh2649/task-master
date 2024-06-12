package com.nashss.se.taskmaster.exceptions;

public class IdNotFoundException extends AttributeNotFoundException {
    private static final long serialVersionUID = 8610377541015309831L;

    public IdNotFoundException() {
        super();
    }

    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException(Throwable cause) {
        super(cause);
    }

    public IdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
