package com.nashss.se.taskmaster.dynamodb;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

@DynamoDBTable(tableName = "tasks")
public class Task {
    private static final String STATUS_INDEX = "GetTasksByStatusIndex";

    private String userId;
    private String id;
    private String desc;
    private Priority priority;
    private String doBy;
    private Status status;
    private Integer points;


    @DynamoDBHashKey(attributeName = "user_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "task_ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "priority")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @DynamoDBAttribute(attributeName = "do_by")
    public String getDoBy() {
        return doBy;
    }

    public void setDoBy(String doBy) {
        this.doBy = doBy;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBIndexHashKey(globalSecondaryIndexName = STATUS_INDEX, attributeName = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @DynamoDBAttribute(attributeName = "points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Task that = (Task) object;
        return  userId.equals(that.userId) &&
                id.equals(that.id) &&
                desc.equals(that.desc) &&
                priority.equals(that.priority) &&
                doBy.equals(that.doBy) &&
                status.equals(that.status) &&
                points.equals(that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, desc, priority, doBy, status, points);
    }
}
