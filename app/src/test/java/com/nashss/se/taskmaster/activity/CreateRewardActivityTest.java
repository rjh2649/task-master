package com.nashss.se.taskmaster.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.taskmaster.activity.request.CreateRewardRequest;
import com.nashss.se.taskmaster.activity.result.CreateRewardResult;
import com.nashss.se.taskmaster.dynamodb.Reward;
import com.nashss.se.taskmaster.dynamodb.dao.RewardDao;

public class CreateRewardActivityTest {
    @Mock
    private RewardDao dao;

    @InjectMocks
    private CreateRewardActivity activity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        activity = new CreateRewardActivity(dao);
    }

    @Test
    void handleRequest_happyPath() {
        // GIVEN
        CreateRewardRequest request = CreateRewardRequest.builder()
                .withUserId("testEmail")
                .withRewardId(UUID.randomUUID().toString())
                .withDesc("test description")
                .withPointsNeeded(500)
                .build();
        
        // WHEN
        CreateRewardResult result = activity.handleRequest(request);

        // THEN
        verify(dao).saveReward(any(Reward.class));
        assertEquals(request.getUserID(), result.getRewardModel().getUserID());
        assertEquals(request.getRewardId(), result.getRewardModel().getRewardId());
        assertEquals(request.getDesc(), result.getRewardModel().getDesc());
        assertEquals(request.getPointsNeeded(), result.getRewardModel().getPointsNeeded());
    }
}
