package com.nashss.se.taskmaster.dynamodb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.exceptions.IdNotFoundException;

@Singleton
public class TaskDao {
    private final DynamoDBMapper mapper;

    @Inject
    public TaskDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Loads a Task from DynamoDB via user's email
     * @param userId the user's email
     * @return Task
     */
    public List<Task> getTasksByUser(String userId) {
        Task task = new Task();
        task.setUserId(userId);

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withHashKeyValues(task);

        
        // if (task == null) {
        //     throw new IdNotFoundException("Tasks could not be founf with userId: " + userId);
        // }
        return mapper.query(Task.class, queryExpression);
    }

    /**
     * Gets all tasks based on the status passed in
     * @param statuses One or more Status values to query by
     * @return a List of Tasks with the specifed status or statuses
     */
    public List<Task> getTasksByStatus(String userId, String... statuses) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        //user_ID = :u AND (status = :s0 OR status = :s1 OR status = :s2)

        String keyConditionExpressionFormat = "user_ID = :userId AND (%s)";
        String statusFormat = "#s = %s";
        String status = ":s";
        for (int i = 0; i < statuses.length; i++) {
            statusFormat = String.format(statusFormat, (status + i));
            valueMap.put(status + i, new AttributeValue().withS(statuses[i]));
            if (i > 1) {
                statusFormat += " OR #s = :s" + (i + 1);
            }
            nameMap.put("#s", "status"); 
        }
        valueMap.put(":userId", new AttributeValue().withS(userId));

        String keyConditionExpression = 
                String.format(keyConditionExpressionFormat, String.format(statusFormat, status));

        System.out.println(valueMap);
        System.out.println(nameMap);
        System.out.println(keyConditionExpression);

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withIndexName("GetTasksByStatusIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression(keyConditionExpression)
                .withExpressionAttributeValues(valueMap)
                .withExpressionAttributeNames(nameMap);

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
