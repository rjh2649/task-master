package com.nashss.se.taskmaster.dynamodb.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;
import com.nashss.se.taskmaster.dynamodb.Task;

public class TaskDaoTest {
    @InjectMocks
    private TaskDao dao;

    @Mock
    private DynamoDBMapper mapper;

    @Captor
    private ArgumentCaptor<DynamoDBQueryExpression<Task>> captor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        dao = new TaskDao(mapper);
    }

    @Test
    void getTasksByStatus_happyPath() {
        // GIVEN
        String notStarted = "NOT_STARTED";
        String pending = "PENDING";
        String completed = "COMPLETED";
        String keyCondtionExpression = "user_ID = :u AND (#s = :s0 OR #s = :s1 OR #s = :s2)";

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":s0", new AttributeValue().withS("NOT_STARTED"));
        valueMap.put(":s1", new AttributeValue().withS("PENDING"));
        valueMap.put(":s2", new AttributeValue().withS("COMPLETED"));

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("#s", "status");

        List<Task> tasks = new ArrayList<>();

        when(mapper.query(eq(Task.class), any(DynamoDBQueryExpression.class))).thenReturn(null);

        // WHEN
        dao.getTasksByStatus("test", notStarted, pending, completed);

        verify(mapper).query(eq(Task.class), captor.capture());

        DynamoDBQueryExpression<Task> actual = captor.getValue();

        // THEN
        System.out.println(actual.toString());

        // assertEquals("GetTasksByStatusIndex", actual.getIndexName());
        // assertEquals(false, actual.isConsistentRead());
        // assertEquals(keyCondtionExpression, actual.getKeyConditionExpression());
        // assertEquals(valueMap, actual.getExpressionAttributeValues());
        // assertEquals(nameMap, actual.getExpressionAttributeNames());
        
    }
}
