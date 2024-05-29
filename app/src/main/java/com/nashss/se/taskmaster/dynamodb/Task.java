package com.nashss.se.taskmaster.dynamodb;

import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

@DynamoDBTable(tableName = "Tasks")
public class Task {
    private String id;
    private String description;
    private Priority priority;
    private String doBy;
    private Status status;
    private Integer points;

    @DynamoDBHashKey(attributeName = "task_id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    @DynamoDBAttribute(attributeName = "priority")
    public String getPriority() {
        return priority.getLevel();
    }

    @DynamoDBAttribute(attributeName = "do_by")
    public String getDoBy() {
        return doBy;
    }

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status.getStatus();
    }

    @DynamoDBAttribute(attributeName = "points")
    public Integer getPoints() {
        return points;
    }
    
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Task task = (Task) object;
        return id.equals(task.id) &&
                description.equals(task.description) &&
                priority.equals(task.priority) &&
                doBy.equals(task.doBy) &&
                status.equals(task.status) &&
                points.equals(task.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, priority, doBy, status, points);
    }
}
