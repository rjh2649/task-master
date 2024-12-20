package com.nashss.se.taskmaster.dependencies;

import javax.inject.Singleton;

import com.nashss.se.taskmaster.activity.CreateRewardActivity;
import com.nashss.se.taskmaster.activity.CreateTaskActivity;
import com.nashss.se.taskmaster.activity.DeleteTaskActivity;
import com.nashss.se.taskmaster.activity.GetRewardsActivity;
import com.nashss.se.taskmaster.activity.GetTaskActivity;
import com.nashss.se.taskmaster.activity.UpdateTaskActivity;

import dagger.Component;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    CreateTaskActivity provideCreateTaskActivity();

    GetTaskActivity provideGetTaskActivity();

    UpdateTaskActivity provideUpdateTaskActivity();

    DeleteTaskActivity provideDeleteTaskActivity();

    CreateRewardActivity provideCreateRewardActivity();

    GetRewardsActivity provideGetRewardsActivity();
}
