package com.nashss.se.taskmaster.activity.result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.models.RewardModel;

public class CreateRewardResult {
    private final Logger log = LogManager.getLogger();
    private final RewardModel rewardModel;

    private CreateRewardResult(RewardModel rewardModel) {
        log.info("RewardModel: {}", rewardModel);
        this.rewardModel = rewardModel;
    }

    public RewardModel getRewardModel() {
        return rewardModel;
    }

    @Override
    public String toString() {
        return "CreateRewardResult{" +
        "RewardModel='" + rewardModel + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private RewardModel rewardModel;

        public Builder withRewardModel(RewardModel rewardModel) {
            this.rewardModel = rewardModel;
            return this;
        }

        public CreateRewardResult build() {
            System.out.println("Building result...");
            CreateRewardResult result = new CreateRewardResult(rewardModel);
            System.out.println(result);
            return result;
        }
    }
}
