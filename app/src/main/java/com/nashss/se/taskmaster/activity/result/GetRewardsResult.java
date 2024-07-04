package com.nashss.se.taskmaster.activity.result;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nashss.se.taskmaster.models.RewardModel;

public class GetRewardsResult {
    private final List<RewardModel> rewardModels;
    private final Logger log = LogManager.getLogger();

    private GetRewardsResult(List<RewardModel> rewardModels) {
        log.info("RewardModels: {}", rewardModels);
        this.rewardModels = rewardModels;
    }

    public List<RewardModel> getRewardModels() {
        return rewardModels;
    }

    @Override
    public String toString() {
        return "GetRewardsResult{" +
        "RewardModels='" + rewardModels + '\'' +
        "}";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<RewardModel> rewardModels;

        public Builder withRewardModels(List<RewardModel> rewardModels) {
            this.rewardModels = rewardModels;
            return this;
        }

        public GetRewardsResult build() {
            System.out.println("Building result...");
            GetRewardsResult result = new GetRewardsResult(rewardModels);
            System.out.println(result);
            return result;
        }
    }
}
