package com.nashss.se.taskmaster.enums;

public enum Priority {
    URGENT_AND_IMPORTANT("Urgent and important"),
    NOT_URGENT_BUT_IMPORTANT("Not urgent but important"),
    URGENT_BUT_NOT_IMPORTANT("Urgent but not important"),
    NOT_URGENT_NOT_IMPORTANT("Not urgent and not important");

    private final String level;

    Priority(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    
}
