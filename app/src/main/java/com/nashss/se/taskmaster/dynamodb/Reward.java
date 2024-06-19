package com.nashss.se.taskmaster.dynamodb;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "rewards")
public class Reward {
    private String userId;
    private String rewardId;
    private String desc;
    private Integer pointsNeeded;

    @DynamoDBHashKey(attributeName = "user_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "reward_ID")
    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @DynamoDBAttribute(attributeName = "points_needed")
    public Integer getPointsNeeded() {
        return pointsNeeded;
    }

    public void setPointsNeeded(Integer pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Reward that = (Reward) object;

        return userId.equals(that.userId) &&
                rewardId.equals(that.rewardId) &&
                desc.equals(that.desc) &&
                pointsNeeded.equals(that.pointsNeeded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, rewardId, desc, pointsNeeded);
    }
}
