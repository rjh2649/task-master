package com.nashss.se.taskmaster.activity;

import java.util.*;

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

        Task updatedTask = new Task();
        List<Task> tasks = dao.getTasksByUser(request.getUserId());

        for (Task task : tasks) {
            if (task.getId().equals(request.getId())) {
                updatedTask.setUserId(request.getUserId());
                updatedTask.setId(request.getId());
                updatedTask.setDesc(request.getDesc());
                updatedTask.setPriority(request.getPriority());
                updatedTask.setDoBy(request.getDoBy());
                updatedTask.setStatus(request.getStatus());
                updatedTask.setPoints(request.getPoints());
            }
        }

        dao.saveTask(updatedTask);

        return UpdateTaskResult.builder()
                .withTaskModel(new ModelConverter().toTaskModel(updatedTask))
                .build();
    }
}
