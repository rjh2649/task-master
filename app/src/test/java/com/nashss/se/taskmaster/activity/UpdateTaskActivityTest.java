package com.nashss.se.taskmaster.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.taskmaster.activity.request.UpdateTaskRequest;
import com.nashss.se.taskmaster.activity.result.UpdateTaskResult;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;
import com.nashss.se.taskmaster.models.TaskModel;

public class UpdateTaskActivityTest {
    @Mock
    private TaskDao dao;

    @InjectMocks
    private UpdateTaskActivity activity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new UpdateTaskActivity(dao);
    }

    @Test
    void handleRequest_happyPath() {
        // GIVEN
        String userId = "email@email.com";
        String taskId = "taskId";
        String desc = "this is a new description";
        Priority priority = Priority.URGENT_AND_IMPORTANT;
        String doBy = "2024/06/30";
        Status status = Status.COMPLETED;
        Integer points = 200;

        UpdateTaskRequest request = UpdateTaskRequest.builder()
                .withUserId(userId)
                .withId(taskId)
                .withDesc(desc)
                .withPriority(priority)
                .withDoBy(doBy)
                .withStatus(status)
                .withPoints(points)
                .build();

        Task toUpdate = new Task();
        toUpdate.setUserId(userId);
        toUpdate.setId(taskId);
        toUpdate.setDesc("this is an old description");
        toUpdate.setPriority(priority);
        toUpdate.setStatus(Status.PENDING);
        toUpdate.setPoints(points);

        when(dao.getTaskByTaskId(userId, taskId)).thenReturn(toUpdate);

        // WHEN
        UpdateTaskResult result = activity.handleRequest(request);

        // THEN
        verify(dao).saveTask(any(Task.class));
        TaskModel updatedTask = result.getTaskModel();
        System.out.println(updatedTask);
        assertEquals(userId, updatedTask.getUserId());
        assertEquals(taskId, updatedTask.getId());
        assertEquals(desc, updatedTask.getDesc());
        assertEquals(priority, updatedTask.getPriority());
        assertEquals(doBy, updatedTask.getDoBy());
        assertEquals(status, updatedTask.getStatus());
        assertEquals(points, updatedTask.getPoints());
    }
}
