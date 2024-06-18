package com.nashss.se.taskmaster.dynamodb.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.taskmaster.dynamodb.Task;

public class TaskDaoTest {
    @InjectMocks
    private TaskDao dao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        dao = new TaskDao(mapper);
    }

    @Test
    void getTasksByStatus_happyPath() {
        String notStarted = "NOT_STARTED";
        String pending = "PENDING";
        String completed = "COMPLETED";

        when(mapper.query(eq(Task.class), any(DynamoDBQueryExpression.class))).thenReturn(null);

        dao.getTasksByStatus("test", notStarted, pending, completed);
    }
}
