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

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new GetTaskActivity(dao);

        task1 = new Task();
        task1.setId("task1");
        task1.setDesc("Task 1");
        task1.setStatus(Status.NOT_STARTED);

        task2 = new Task();
        task2.setId("task2");
        task2.setDesc("Task 2");
        task2.setStatus(Status.PENDING);

        task3 = new Task();
        task3.setId("task3");
        task3.setDesc("Task 3");
        task3.setStatus(Status.COMPLETED);
    }

    @Test
    void handleRequest_withPendingAndCompletedTasks_returnsResult() {
        // GIVEN
        List<Task> pendingTasks = new ArrayList<>();
        pendingTasks.add(task1);
        pendingTasks.add(task2);

        List<Task> completedTasks = new ArrayList<>();
        completedTasks.add(task3);

        when(dao.getTasksByStatus("NOT_STARTED", "PENDING")).thenReturn(pendingTasks);
        when(dao.getTasksByStatus("COMPLETED")).thenReturn(completedTasks);

        // WHEN
        GetTaskResult result = activity.handleRequest();

        // THEN
        assertNotNull(result);
        assertEquals(2, result.getPending().size());
        assertEquals(1, result.getCompleted().size());
    }
}
