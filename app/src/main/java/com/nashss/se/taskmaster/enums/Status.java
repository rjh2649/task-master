package com.nashss.se.taskmaster.enums;

public enum Status {
    NOT_STARTED("Not started"),
    PENDING("Pending"),
    COMPLETED("Completed");

    private final String status;
    
    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
