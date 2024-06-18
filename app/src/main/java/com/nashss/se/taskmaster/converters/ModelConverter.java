package com.nashss.se.taskmaster.converters;

import java.util.ArrayList;
import java.util.List;

import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.models.TaskModel;

public class ModelConverter {
    public TaskModel toTaskModel(Task task) {
        return TaskModel.builder()
                .withUserId(task.getUserId())
                .withId(task.getId())
                .withDesc(task.getDesc())
                .withPriority(task.getPriority())
                .withDoBy(task.getDoBy())
                .withStatus(task.getStatus())
                .withPoints(task.getPoints())
                .build();
    }

    public List<TaskModel> toTaskModelList(List<Task> tasks) {
        List<TaskModel> taskModels = new ArrayList<>();

        tasks.forEach(task -> {
            taskModels.add(TaskModel.builder()
                            .withUserId(task.getUserId())
                            .withId(task.getId())
                            .withDesc(task.getDesc())
                            .withPriority(task.getPriority())
                            .withDoBy(task.getDoBy())
                            .withStatus(task.getStatus())
                            .withPoints(task.getPoints())
                            .build());
        });

        return taskModels;
    }
}
