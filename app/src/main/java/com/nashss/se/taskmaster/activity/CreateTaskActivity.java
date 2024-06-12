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

    /**
     * Gets the relevant info from the request and saves it as a new item in the Tasks table.
     * @param request the request to create a Task item
     * @return CreateTaskResult
     */
    public CreateTaskResult handleRequest(final CreateTaskRequest request) {
        log.info("Received CreateTaskRequest {}", request);

        Task newTask = new Task();
        newTask.setId(request.getId());
        newTask.setDesc(request.getDesc());
        newTask.setPriority(request.getPriority());
        newTask.setDoBy(request.getDoBy());
        newTask.setStatus(request.getStatus());
        newTask.setPoints(request.getPoints());
        
        dao.saveTask(newTask);

        TaskModel model = new ModelConverter().toTaskModel(newTask);
        
        return CreateTaskResult.builder()
                .withTaskModel(model)
                .build();
    }
}
