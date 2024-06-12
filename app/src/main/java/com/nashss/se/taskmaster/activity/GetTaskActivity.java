package com.nashss.se.taskmaster.activity;

import java.util.*;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.result.GetTaskResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;
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
    public GetTaskResult handleRequest() {
        log.info("Received GetTaskRequest");
        
        List<Task> pendingTasks = dao.getTasksByStatus("NOT_STARTED", "PENDING");
        List<Task> completedTasks = dao.getTasksByStatus("COMPLETED");

        List<TaskModel> pendingModels = pendingTasks.stream()
                .map(task -> new ModelConverter().toTaskModel(task))
                .collect(Collectors.toList());
        
        List<TaskModel> completedModels = new ArrayList<>();
        if (!completedTasks.isEmpty()) {
            completedModels = completedTasks.stream()
                    .map(task -> new ModelConverter().toTaskModel(task))
                    .collect(Collectors.toList());
        }

        
        return GetTaskResult.builder()
                .withPendingTasks(pendingModels)
                .withCompletedTasks(completedModels)
                .build();
    }
}
