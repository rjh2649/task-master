package com.nashss.se.taskmaster.enums;

public enum Status {
    NOT_STARTED("NOT_STARTED"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED");

    private final String status;
    
    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public Status getDefault() {
        return NOT_STARTED;
    }
}
