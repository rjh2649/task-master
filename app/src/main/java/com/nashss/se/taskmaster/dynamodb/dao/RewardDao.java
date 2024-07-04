package com.nashss.se.taskmaster.dynamodb.dao;

import java.util.*;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.taskmaster.dynamodb.Reward;

@Singleton
public class RewardDao {
    private final DynamoDBMapper mapper;

    @Inject
    public RewardDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }
    
    /**
     * Saves the passed in reward to the DynamoDB table
     * @param reward the reward to save
     */
    public void saveReward(Reward reward) {
        mapper.save(reward);
    }

    /**
     * Gets all rewards with the specified userId from the table
     * @param userId
     * @return a PaginatedQueryList of rewards
     */
    public List<Reward> getRewardsByUserId(String userId) {
        Reward reward = new Reward();
        reward.setUserId(userId);

        DynamoDBQueryExpression<Reward> queryExpression = new DynamoDBQueryExpression<Reward>()
                .withHashKeyValues(reward);
        
        return mapper.query(Reward.class, queryExpression);
    }
}
