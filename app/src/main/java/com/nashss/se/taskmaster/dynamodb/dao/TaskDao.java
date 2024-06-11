package com.nashss.se.taskmaster.dynamodb.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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
