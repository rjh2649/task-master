package com.nashss.se.taskmaster.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

@JsonDeserialize()
public class CreateTaskRequest {
    private final String id;
    private final String description;
    private final Priority priority;
    private final String doBy;
    private final Status status;
    private final Integer points;

    private CreateTaskRequest(String id, String description, Priority priority, String doBy, Status status, Integer points) {
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
    public String toString() {
        return "CreateTaskRequest{" +
        "task='" + id + '\'' +
        "description='" + description + '\'' +
        "priority='" + priority.getLevel() + '\'' +
        "doBy='" + doBy + '\'' +
        "status='" + status.getStatus() + '\'' +
        "points='" + points + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
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

        public CreateTaskRequest build() {
            return new CreateTaskRequest(id, description, priority, doBy, status, points);
        }
    }
}
