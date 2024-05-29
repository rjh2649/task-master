package com.nashss.se.taskmaster.models;

import java.util.Objects;

import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

public class TaskModel {
    private final String id;
    private final String description;
    private final Priority priority;
    private final String doBy;
    private final Status status;
    private final Integer points;

    private TaskModel(String id, String description, Priority priority, String doBy, Status status, Integer points) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.doBy = doBy;
        this.status = status;
        this.points = points;
    }

    public String getId() {
        return id;
    }
    
    public String getDescription() {
        return description;
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
        TaskModel taskModel = (TaskModel) object;
        return id.equals(taskModel.id) &&
                description.equals(taskModel.description) &&
                priority.equals(taskModel.priority) &&
                doBy.equals(taskModel.doBy) &&
                status.equals(taskModel.status) &&
                points.equals(taskModel.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority, doBy, status, points);
    }

    public static Builder builder() {
        return null;
    }

    public static class Builder {
        private String id;
        private String description;
        private Priority priority;
        private String doBy;
        private Status status;
        private Integer points;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder wtihPriority(Priority priority) {
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
            return new TaskModel(id, description, priority, doBy, status, points);
        }
    }
}
