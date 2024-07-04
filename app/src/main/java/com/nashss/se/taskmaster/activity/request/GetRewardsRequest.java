package com.nashss.se.taskmaster.activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetRewardsRequest.Builder.class)
public class GetRewardsRequest {
    private final String userId;

    private GetRewardsRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetRewardsRequest{" +
        "userId='" + userId + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetRewardsRequest build() {
            System.out.println("Building request...");
            GetRewardsRequest request = new GetRewardsRequest(userId);
            System.out.println(request);
            return request;
        }
    }
}
