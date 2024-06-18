package com.nashss.se.taskmaster.models;

import java.util.Objects;

public class RewardModel {
    private final String userId;
    private final String rewardId;
    private final String desc;
    private final Integer pointsNeeded;

    private RewardModel(String userId, String rewardId, String desc, Integer pointsNeeded) {
        this.userId = userId;
        this.rewardId = rewardId;
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
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        RewardModel that = (RewardModel) object;
        
        return userId.equals(that.userId) &&
                rewardId.equals(that.rewardId) &&
                desc.equals(that.desc) &&
                pointsNeeded.equals(that.pointsNeeded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, rewardId, desc, pointsNeeded);
    }

    public static Builder builder() {
        return new Builder();
    }

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

        public RewardModel build() {
            return new RewardModel(userId, rewardId, desc, pointsNeeded);
        }
    }
}
