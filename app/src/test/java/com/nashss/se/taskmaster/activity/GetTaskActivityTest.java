package com.nashss.se.taskmaster.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.taskmaster.activity.request.GetTaskRequest;
import com.nashss.se.taskmaster.activity.result.GetTaskResult;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;
import com.nashss.se.taskmaster.enums.Status;

public class GetTaskActivityTest {
    @Mock
    private TaskDao dao;

    @InjectMocks
    private GetTaskActivity activity;

    private Task task1;
    private Task task2;
    private Task task3;

    GetTaskRequest request1;
    GetTaskRequest request2;
    GetTaskRequest request3;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new GetTaskActivity(dao);

        request1 = GetTaskRequest.builder()
                .withUserId("user1")
                .withStatus(Status.NOT_STARTED)
                .build();
        // request2 = GetTaskRequest.builder()
        //         .withUserId("user1")
        //         .withStatus(Status.PENDING)
        //         .build();
        request3 = GetTaskRequest.builder()
                .withUserId("user1")
                .withStatus(Status.COMPLETED)
                .build();

        task1 = new Task();
        task1.setUserId(request1.getUserId());
        task1.setStatus(request1.getStatus());

        // task2 = new Task();
        // task2.setUserId(request2.getUserId());
        // task2.setStatus(request2.getStatus());

        task3 = new Task();
        task3.setId(request3.getUserId());
        task3.setStatus(request3.getStatus());
    }

    @Test
    void handleRequest_withPendingAndCompletedTasks_returnsResult() {
        // GIVEN
        List<Task> pendingTasks = new ArrayList<>();
        pendingTasks.add(task1);
        // pendingTasks.add(task2);

        List<Task> completedTasks = new ArrayList<>();
        completedTasks.add(task3);

        when(dao.getTasksByStatus(request1.getUserId(), request1.getStatus().toString())).thenReturn(pendingTasks);
        when(dao.getTasksByStatus(request3.getUserId(), request3.getStatus().toString())).thenReturn(completedTasks);

        // WHEN
        GetTaskResult result1 = activity.handleRequest(request1);
        // GetTaskResult result2 = activity.handleRequest(request2);
        GetTaskResult result3 = activity.handleRequest(request3);

        // THEN
        System.out.println(result1.getPending());
        // System.out.println(result2.getPending());
        System.out.println(result3.getCompleted());
        assertEquals(1, result1.getPending().size());
        // assertEquals(2, result2.getPending().size());
        assertEquals(1, result3.getCompleted().size());
    }
}
