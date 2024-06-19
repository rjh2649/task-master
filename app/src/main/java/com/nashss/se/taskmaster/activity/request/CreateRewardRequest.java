package com.nashss.se.taskmaster.activity.request;

import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateRewardRequest.Builder.class)
public class CreateRewardRequest {
    private final String userId;
    private final String rewardId;
    private final String desc;
    private final Integer pointsNeeded;

    private CreateRewardRequest(String userId, String rewardId, String desc, Integer pointsNeeded) {
        this.userId = userId;
        this.rewardId = UUID.randomUUID().toString();
        this.desc = desc;
        this.pointsNeeded = pointsNeeded;
    }

    public String getUserID() {
        return userId;
    }

    public String getRewardId() {
        return rewardId;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getPointsNeeded() {
        return pointsNeeded;
    }

    @Override
    public String toString() {
        return "CreateRewardRequest{" +
        "userId='" + userId + '\'' +
        "rewardId='" + rewardId + '\'' +
        "description='" + desc + '\'' +
        "pointsNeeded='" + pointsNeeded + 
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;
        private String rewardId;
        private String desc;
        private Integer pointsNeeded;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withRewardId(String rewardId) {
            this.rewardId = rewardId;
            return this;
        }

        public Builder withDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder withPointsNeeded(Integer pointsNeeded) {
            this.pointsNeeded = pointsNeeded;
            return this;
        }

        public CreateRewardRequest build() {
            return new CreateRewardRequest(userId, rewardId, desc, pointsNeeded);
        }
    }
}
