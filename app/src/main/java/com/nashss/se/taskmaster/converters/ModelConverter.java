package com.nashss.se.taskmaster.converters;

import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.models.TaskModel;

public class ModelConverter {
    public TaskModel toTaskModel(Task task) {
        return TaskModel.builder()
                .withEmail(task.getEmail())
                .withTask(task.getTask())
                .withPriority(task.getPriority())
                .withDoBy(task.getDoBy())
                .withStatus(task.getStatus())
                .withPoints(task.getPoints())
                .build();
    }
}
