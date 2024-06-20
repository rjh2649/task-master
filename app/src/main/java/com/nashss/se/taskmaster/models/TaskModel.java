package com.nashss.se.taskmaster.models;

import java.util.Objects;

import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

public class TaskModel {
    private final String userId;
    private final String id;
    private final String desc;
    private final Priority priority;
    private final String doBy;
    private final Status status;
    private final Integer points;

    private TaskModel(String userId, String id, String desc, Priority priority, String doBy, Status status, Integer points) {
        this.userId = userId;
        this.id = id;
        this.desc = desc;
        this.priority = priority;
        this.doBy = doBy;
        this.status = status;
        this.points = points;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }
    
    public String getDesc() {
        return desc;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getDoBy() {
        return doBy;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        TaskModel that = (TaskModel) object;
        return userId.equals(that.userId) &&
                id.equals(that.id) &&
                desc.equals(that.desc) &&
                priority.equals(that.priority) &&
                doBy.equals(that.doBy) &&
                status.equals(that.status) &&
                points.equals(that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, priority, doBy, status, points);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String id;
        private String desc;
        private Priority priority;
        private String doBy;
        private Status status;
        private Integer points;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        } 

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder withDoBy(String doBy) {
            this.doBy = doBy;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder withPoints(Integer points) {
            this.points = points;
            return this;
        }

        public TaskModel build() {
            return new TaskModel(userId, id, desc, priority, doBy, status, points);
        }
    }
}
