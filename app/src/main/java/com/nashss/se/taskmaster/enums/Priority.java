package com.nashss.se.taskmaster.enums;

public enum Priority {
    URGENT_AND_IMPORTANT(200),
    NOT_URGENT_BUT_IMPORTANT(150),
    URGENT_BUT_NOT_IMPORTANT(100),
    NOT_URGENT_NOT_IMPORTANT(50);

    private final Integer points;

    Priority(Integer points) {
        this.points = points;
    }

    public Integer getPointsForPriority() {
        return points;
    }

    
}
