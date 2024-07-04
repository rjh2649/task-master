package com.nashss.se.taskmaster.dynamodb.dao;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.taskmaster.dynamodb.Reward;

@Singleton
public class RewardDao {
    private final DynamoDBMapper mapper;

    @Inject
    public RewardDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public void saveReward(Reward reward) {
        mapper.save(reward);
    }
}
