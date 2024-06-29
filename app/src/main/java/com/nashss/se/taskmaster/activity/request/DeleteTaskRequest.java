package com.nashss.se.taskmaster.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = DeleteTaskRequest.Builder.class)
public class DeleteTaskRequest {
    private final String userId;
    private final String id;

    private DeleteTaskRequest(String userId, String id) {
        this.userId = userId;
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeleteTaskRequest{" + 
        "userId='" + userId + '\'' +
        "id='" + id + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String id;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        
        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public DeleteTaskRequest build() {
            return new DeleteTaskRequest(userId, id);
        }
    }
}
