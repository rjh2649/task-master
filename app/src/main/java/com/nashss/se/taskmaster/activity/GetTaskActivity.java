package com.nashss.se.taskmaster.activity;

import java.util.*;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.request.GetTaskRequest;
import com.nashss.se.taskmaster.activity.result.GetTaskResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;
import com.nashss.se.taskmaster.enums.Status;
import com.nashss.se.taskmaster.models.TaskModel;

public class GetTaskActivity {
    private final Logger log = LogManager.getLogger();
    private final TaskDao dao;

    @Inject
    public GetTaskActivity(TaskDao dao) {
        this.dao = dao;
    }

    /**
     * Gets all Tasks and organizes them based on status
     * @return GetTaskResult
     */
    public GetTaskResult handleRequest(final GetTaskRequest request) {
        log.info("Received GetTaskRequests");

        List<Task> pendingTasks = new ArrayList<>();
        List<Task> completedTasks = new ArrayList<>();

        List<TaskModel> pendingModels = new ArrayList<>();
        List<TaskModel> completedModels = new ArrayList<>();

        if (request.getStatus().equals(Status.NOT_STARTED) || request.getStatus().equals(Status.PENDING)) {
            pendingTasks = dao.getTasksByStatus(request.getUserId(), request.getStatus().toString());
            pendingModels = new ModelConverter().toTaskModelList(pendingTasks);
        }

        if (request.getStatus().equals(Status.COMPLETED)) {
            completedTasks = dao.getTasksByStatus(request.getUserId(), request.getStatus().toString());
            completedModels = new ModelConverter().toTaskModelList(completedTasks);
        }
        
        return GetTaskResult.builder()
                .withPendingTasks(pendingModels)
                .withCompletedTasks(completedModels)
                .build();
    }
}
