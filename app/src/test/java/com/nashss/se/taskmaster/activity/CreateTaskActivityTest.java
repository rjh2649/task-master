package com.nashss.se.taskmaster.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.taskmaster.activity.request.CreateTaskRequest;
import com.nashss.se.taskmaster.activity.result.CreateTaskResult;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;
import com.nashss.se.taskmaster.enums.Priority;
import com.nashss.se.taskmaster.enums.Status;

public class CreateTaskActivityTest {
    @Mock
    private TaskDao dao;

    private CreateTaskActivity activity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new CreateTaskActivity(dao);
    }

    @Test
    void handleRequest_withValidFields_createsTask() {
        //GIVEN
        CreateTaskRequest request = CreateTaskRequest.builder()
                                    .withEmail("email@email.com")
                                    .withTask("Sample Task")
                                    .withPriority(Priority.URGENT_AND_IMPORTANT)
                                    .withDoBy("2024-06-30")
                                    .withStatus(Status.NOT_STARTED)
                                    .withPoints(100)
                                    .build();
        
        //WHEN
        CreateTaskResult result = activity.handleRequest(request);

        //THEN
        verify(dao).saveTask(any(Task.class));
        assertNotNull(result.getTaskModel());
        assertEquals("email@email.com", result.getTaskModel().getEmail());
    }
}
