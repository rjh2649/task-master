package com.nashss.se.taskmaster.converters;

import java.util.ArrayList;
import java.util.List;

import com.nashss.se.taskmaster.dynamodb.Reward;
import com.nashss.se.taskmaster.dynamodb.Task;
import com.nashss.se.taskmaster.models.RewardModel;
import com.nashss.se.taskmaster.models.TaskModel;

public class ModelConverter {
    public TaskModel toTaskModel(Task task) {
        return TaskModel.builder()
                .withUserId(task.getUserId())
                .withId(task.getId())
                .withDesc(task.getDesc())
                .withPriority(task.getPriority())
                .withDoBy(task.getDoBy())
                .withStatus(task.getStatus())
                .withPoints(task.getPoints())
                .build();
    }

    public List<TaskModel> toTaskModelList(List<Task> tasks) {
        List<TaskModel> taskModels = new ArrayList<>();

        tasks.forEach(task -> {
            taskModels.add(TaskModel.builder()
                            .withUserId(task.getUserId())
                            .withId(task.getId())
                            .withDesc(task.getDesc())
                            .withPriority(task.getPriority())
                            .withDoBy(task.getDoBy())
                            .withStatus(task.getStatus())
                            .withPoints(task.getPoints())
                            .build());
        });

        return taskModels;
    }

    public RewardModel toRewardModel(Reward reward) {
        return RewardModel.builder()
                .withUserId(reward.getUserId())
                .withRewardId(reward.getRewardId())
                .withDesc(reward.getDesc())
                .withPointsNeeded(reward.getPointsNeeded())
                .build();
    }

    public List<RewardModel> toRewardModels(List<Reward> rewards) {
        List<RewardModel> rewardModels = new ArrayList<>();

        rewards.forEach(reward -> {
            rewardModels.add(RewardModel.builder()
                            .withUserId(reward.getUserId())
                            .withRewardId(reward.getRewardId())
                            .withDesc(reward.getDesc())
                            .withPointsNeeded(reward.getPointsNeeded())
                            .build());
        });

        return rewardModels;
    }
}
