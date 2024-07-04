package com.nashss.se.taskmaster.activity;

import java.util.*;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.activity.request.GetRewardsRequest;
import com.nashss.se.taskmaster.activity.result.GetRewardsResult;
import com.nashss.se.taskmaster.converters.ModelConverter;
import com.nashss.se.taskmaster.dynamodb.Reward;
import com.nashss.se.taskmaster.dynamodb.dao.RewardDao;
import com.nashss.se.taskmaster.exceptions.IdNotFoundException;
import com.nashss.se.taskmaster.utils.EmailUtil;

public class GetRewardsActivity {
    private final Logger log = LogManager.getLogger();
    private final RewardDao dao;

    @Inject
    public GetRewardsActivity(RewardDao dao) {
        this.dao = dao;
    }

    public GetRewardsResult handleRequest(final GetRewardsRequest request) {
        log.info("Received GetRewardsRequest: {}", request);

        String email = request.getUserId();

        if (!EmailUtil.isValidEmail(email)) {
            throw new IdNotFoundException("Email is invalid");
        }
        
        List<Reward> rewards = dao.getRewardsByUserId(email);

        return GetRewardsResult.builder()
                .withRewardModels(new ModelConverter().toRewardModels(rewards))
                .build();
    }
}
