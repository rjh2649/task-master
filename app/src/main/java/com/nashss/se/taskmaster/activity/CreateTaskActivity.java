package com.nashss.se.taskmaster.activity;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.request.CreateTaskRequest;
import com.nashss.se.taskmaster.activity.result.CreateTaskResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;
import com.nashss.se.taskmaster.models.TaskModel;

public class CreateTaskActivity {
    private final Logger log = LogManager.getLogger();
    private final TaskDao dao;

    @Inject
    public CreateTaskActivity(TaskDao dao) {
        this.dao = dao;
    }

    public CreateTaskResult handleRequest(final CreateTaskRequest request) {
        log.info("Received CreateTaskRequest {}", request);

        Task task = new Task();
        task.setId(request.getId());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDoBy(request.getDoBy());
        task.setStatus(request.getStatus());
        task.setPoints(request.getPoints());
        
        dao.saveTask(task);

        TaskModel model = new ModelConverter().toTaskModel(task);
        
        return CreateTaskResult.builder()
                .withTaskModel(model)
                .build();
    }
}
