package com.nashss.se.taskmaster.exceptions;

public class AttributeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1556318695331125354L;

    public AttributeNotFoundException() {
        super();
    }
    
    public AttributeNotFoundException(String message) {
        super(message);
    }

    public AttributeNotFoundException(Throwable cause) {
        super(cause);
    }

    public AttributeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
