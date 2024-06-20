package com.nashss.se.taskmaster.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.taskmaster.activity.request.DeleteTaskRequest;
import com.nashss.se.taskmaster.activity.result.DeleteTaskResult;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;

public class DeleteTaskActivityTest {
    @Mock
    TaskDao dao;

    @InjectMocks
    DeleteTaskActivity activity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new DeleteTaskActivity(dao);
    }

    @Test
    void handleRequest_happyPath() {
        // GIVEN
        String userId = "email@email.com";
        String id = "taskId";

        DeleteTaskRequest request = DeleteTaskRequest.builder()
                .withUserId(userId)
                .withId(id)
                .build();

        Task toDelete = new Task();
        toDelete.setUserId(userId);
        toDelete.setId(id);
        
        when(dao.getTaskByTaskId(request.getUserId(), request.getId())).thenReturn(toDelete);
        
        // WHEN
        DeleteTaskResult result = activity.handleRequest(request);

        // THEN
        verify(dao).deleteTask(toDelete);
        assertEquals(userId, result.getTaskModel().getUserId());
        assertEquals(id, result.getTaskModel().getId());
    }
}
