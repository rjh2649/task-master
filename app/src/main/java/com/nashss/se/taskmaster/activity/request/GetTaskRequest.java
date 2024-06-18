package com.nashss.se.taskmaster.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.taskmaster.enums.Status;

@JsonDeserialize(builder = GetTaskRequest.Builder.class)
public class GetTaskRequest {
    private final String userId;
    private final Status status;

    private GetTaskRequest(String userId, Status status) {
        this.userId = userId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "GetTaskRequest{" +
        "email='" + userId + '\'' +
        "status='" + status + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private Status status;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public GetTaskRequest build() {
            return new GetTaskRequest(userId, status);
        }
    }
}
