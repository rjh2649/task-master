package com.nashss.se.taskmaster.models;

import java.util.Objects;

import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

public class TaskModel {
    private final String id;
    private final String desc;
    private final Priority priority;
    private final String doBy;
    private final Status status;
    private final Integer points;

    private TaskModel(String id, String desc, Priority priority, String doBy, Status status, Integer points) {
        this.id = id;
        this.desc = desc;
        this.priority = priority;
        this.doBy = doBy;
        this.status = status;
        this.points = points;
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
        return id.equals(that.id) &&
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
        private String email;
        private String task;
        private Priority priority;
        private String doBy;
        private Status status;
        private Integer points;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withTask(String task) {
            this.task = task;
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
            return new TaskModel(email, task, priority, doBy, status, points);
        }
    }
}
