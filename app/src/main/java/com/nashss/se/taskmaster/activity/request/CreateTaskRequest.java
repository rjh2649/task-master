package com.nashss.se.taskmaster.activity.request;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

@JsonDeserialize(builder = CreateTaskRequest.Builder.class)
public class CreateTaskRequest {
    private final String id;
    private final String desc;
    private final Priority priority;
    private final String doBy;
    private final Status status;
    private final Integer points;

    private CreateTaskRequest(String id, String desc, Priority priority, String doBy, Status status, Integer points) {
        this.id = UUID.randomUUID().toString();
        this.desc = desc;
        this.priority = priority;
        this.doBy = doBy;
        this.status = status.getDefault();
        this.points = priority.getPointsForPriority();
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
    public String toString() {
        return "CreateTaskRequest{" +
        "id='" + id + '\'' +
        "description'" + desc + '\'' +
        "priority='" + priority + '\'' +
        "doBy='" + doBy + '\'' +
        "status='" + status + '\'' +
        "points='" + points + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String desc;
        private Priority priority;
        private String doBy;
        private Status status;
        private Integer points;

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

        public CreateTaskRequest build() {
            System.out.println("Building request...");
            CreateTaskRequest request = new CreateTaskRequest(id, desc, priority, doBy, status, points);
            System.out.println(request);
            return request;
        }
    }
}
