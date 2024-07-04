package com.nashss.se.taskmaster.activity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nashss.se.taskmaster.activity.request.GetRewardsRequest;
import com.nashss.se.taskmaster.activity.result.GetRewardsResult;
import com.nashss.se.taskmaster.dynamodb.Reward;
import com.nashss.se.taskmaster.dynamodb.dao.RewardDao;

public class GetRewardsActivityTest {
    @Mock
    private RewardDao dao;

    @InjectMocks
    private GetRewardsActivity activity;

    private GetRewardsRequest request;
    private Reward reward;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.activity = new GetRewardsActivity(dao);

        request = GetRewardsRequest.builder()
                .withUserId("email@email.com")
                .build();

        reward = new Reward();
        reward.setUserId(request.getUserId());
    }

    @Test
    void handleRequest_happyPath() {
        // GIVEN
        List<Reward> rewards = new ArrayList<>();
        rewards.add(reward);
        
        when(dao.getRewardsByUserId(request.getUserId())).thenReturn(rewards);

        // WHEN
        GetRewardsResult result = activity.handleRequest(request);

        // THEN
        assertEquals(rewards.get(0).getUserId(), result.getRewardModels().get(0).getUserID());
    }
}
