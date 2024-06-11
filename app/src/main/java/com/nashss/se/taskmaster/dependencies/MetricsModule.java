package com.nashss.se.taskmaster.dependencies;

import javax.inject.Singleton;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;

import dagger.Module;
import dagger.Provides;

@Module
public class MetricsModule {
    @Provides
    @Singleton
    static AmazonCloudWatch provideCloudWatch() {
        return AmazonCloudWatchAsyncClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .build();
    }
}
