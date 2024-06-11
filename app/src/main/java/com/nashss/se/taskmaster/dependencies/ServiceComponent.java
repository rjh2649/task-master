package com.nashss.se.taskmaster.dependencies;

import javax.inject.Singleton;

import com.nashss.se.taskmaster.activity.CreateTaskActivity;

import dagger.Component;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {
    CreateTaskActivity provideCreateTaskActivity();
}
