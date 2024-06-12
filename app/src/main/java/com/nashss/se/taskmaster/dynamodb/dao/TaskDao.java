package com.nashss.se.taskmaster.dynamodb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.checkerframework.checker.units.qual.A;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.taskmaster.dynamodb.Task;

@Singleton
public class TaskDao {
    private final DynamoDBMapper mapper;

    @Inject
    public TaskDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Loads a Task from DynamoDB via TaskId
     * @param taskId the name of the task
     * @return Task
     */
    public Task getTask(String taskId) {
        if (taskId == null) {
            throw new RuntimeException();
        }

        Task task = mapper.load(Task.class, taskId);

        if (task == null) {
            throw new RuntimeException();
        }

        return task;
    }

    /**
     * Gets all tasks based on the status passed in
     * @param statuses One or more Status values to query by
     * @return a List of Tasks with the specifed status or statuses
     */
    public List<Task> getTasksByStatus(String... statuses) {
        Map<String, AttributeValue> valueMap = new HashMap<>();

        String keyConditionExpression = "status IN (";
        for (int i = 0; i < statuses.length; i++) {
            String attributeName = ":status" + i;
            valueMap.put(attributeName, new AttributeValue().withS(statuses[i]));
            keyConditionExpression += attributeName;
            if (i < statuses.length - 1) {
                keyConditionExpression += ", ";
            } 
        }
        keyConditionExpression += ")";

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withIndexName("GetTasksByStatusIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression(keyConditionExpression)
                .withExpressionAttributeValues(valueMap);

        return mapper.query(Task.class, queryExpression);
    }
    
    /**
     * Saves a Task to DynamoDB
     * @param task
     */
    public void saveTask(Task task) {
        mapper.save(task);
    }
    
    /**
     * Deletes a Task from DynamoDB
     * @param task
     */
    public void deleteTask(Task task) {
        mapper.delete(task);
    }
}
