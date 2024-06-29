package com.nashss.se.taskmaster.activity;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.request.DeleteTaskRequest;
import com.nashss.se.taskmaster.activity.result.DeleteTaskResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;

public class DeleteTaskActivity {
    private final Logger log = LogManager.getLogger();
    private final TaskDao dao;

    @Inject
    public DeleteTaskActivity(TaskDao dao) {
        this.dao = dao;
    }

    public DeleteTaskResult handleRequest(DeleteTaskRequest request) {
        log.info("Received DeleteTaskRequest: {}", request);
        
        Task toDelete = dao.getTaskByTaskId(request.getUserId(), request.getId());

        dao.deleteTask(toDelete);
        
        return DeleteTaskResult.builder()
                .withTaskModel(new ModelConverter().toTaskModel(toDelete))
                .build();
    }
}
