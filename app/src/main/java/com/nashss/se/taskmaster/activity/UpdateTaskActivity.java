package com.nashss.se.taskmaster.activity;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.request.UpdateTaskRequest;
import com.nashss.se.taskmaster.activity.result.UpdateTaskResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;

public class UpdateTaskActivity {
    private final Logger log = LogManager.getLogger();
    private final TaskDao dao;

    @Inject
    public UpdateTaskActivity(TaskDao dao) {
        this.dao = dao;
    }

    public UpdateTaskResult handleRequest(UpdateTaskRequest request) {
        log.info("Received UpdateTaskRequest: {}", request);

        System.out.println(request.getUserId());
        System.out.println(request.getId() + ": print 2");

        Task updatedTask = new Task();
        Task taskToUpdate = dao.getTaskByTaskId(request.getUserId(), request.getId());

        updatedTask.setUserId(request.getUserId());
        updatedTask.setId(request.getId());
        updatedTask.setPoints(request.getPoints());
        
        if (request.getDesc() == null) {
            updatedTask.setDesc(taskToUpdate.getDesc());
        } else {
            updatedTask.setDesc(request.getDesc());
        }
        if (request.getPriority() == null) {
            updatedTask.setPriority(taskToUpdate.getPriority());
        } else {
            updatedTask.setPriority(request.getPriority());
        }
        if (request.getDoBy() == null) {
            updatedTask.setDoBy(taskToUpdate.getDoBy());
        } else {
            updatedTask.setDoBy(request.getDoBy());
        } 
        if (request.getStatus() == null) {
            updatedTask.setStatus(taskToUpdate.getStatus());
        } else {
            updatedTask.setStatus(request.getStatus());
        }

        dao.saveTask(updatedTask);

        UpdateTaskResult result = UpdateTaskResult.builder()
                .withTaskModel(new ModelConverter().toTaskModel(updatedTask))
                .build();

        System.out.println(result.getTaskModel().getId() + ": print 4");

        return result;
    }
}
