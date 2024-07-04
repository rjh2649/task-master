package com.nashss.se.taskmaster.activity;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.request.CreateRewardRequest;
import com.nashss.se.taskmaster.activity.result.CreateRewardResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Reward;
import com.nashss.se.taskmaster.dynamodb.dao.RewardDao;

public class CreateRewardActivity {
    private final Logger log = LogManager.getLogger();
    private final RewardDao dao;

    @Inject
    public CreateRewardActivity(RewardDao dao) {
        this.dao = dao;
    }

    public CreateRewardResult handleRequest(final CreateRewardRequest request) {
        log.info("CreateRewardRequest: {}", request);

        Reward reward = new Reward();
        reward.setUserId(request.getUserID());
        reward.setRewardId(request.getRewardId());
        reward.setDesc(request.getDesc());
        reward.setPointsNeeded(request.getPointsNeeded());

        dao.saveReward(reward);

        return CreateRewardResult.builder()
                .withRewardModel(new ModelConverter().toRewardModel(reward))
                .build();
    }
}
