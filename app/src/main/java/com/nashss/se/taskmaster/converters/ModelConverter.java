package com.nashss.se.taskmaster.converters;

import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.models.TaskModel;

public class ModelConverter {
    public TaskModel toTaskModel(Task task) {
        return TaskModel.builder()
                .withId(task.getId())
                .withDescription(task.getDescription())
                .withPriority(task.getPriority())
                .withDoBy(task.getDoBy())
                .withStatus(task.getStatus())
                .withPoints(task.getPoints())
                .build();
    }
}
