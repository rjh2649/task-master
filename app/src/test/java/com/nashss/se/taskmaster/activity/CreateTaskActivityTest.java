package com.nashss.se.taskmaster.activity;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.nashss.se.taskmaster.dynamodb.dao.TaskDao;

public class CreateTaskActivityTest {
    @Mock
    private TaskDao dao;

    @InjectMocks
    private CreateTaskActivity activity;

    @BeforeEach
    void setup() {
        
    }
}
