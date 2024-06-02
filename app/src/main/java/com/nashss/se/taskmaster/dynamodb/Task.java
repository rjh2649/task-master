package com.nashss.se.taskmaster.dynamodb;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

@DynamoDBTable(tableName = "Tasks")
public class Task {
    private String email;
    private String task;
    private Priority priority;
    private String doBy;
    private Status status;
    private Integer points;

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBRangeKey(attributeName = "task")
    public String getTask() {
        return task;
    }

    public void setDescription(String description) {
        this.task = description;
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

    @DynamoDBAttribute(attributeName = "status")
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
        return email.equals(that.email) &&
                task.equals(that.task) &&
                priority.equals(that.priority) &&
                doBy.equals(that.doBy) &&
                status.equals(that.status) &&
                points.equals(that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, task, priority, doBy, status, points);
    }
}
