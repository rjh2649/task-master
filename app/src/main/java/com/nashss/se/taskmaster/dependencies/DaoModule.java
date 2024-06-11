package com.nashss.se.taskmaster.dependencies;

import javax.inject.Singleton;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.taskmaster.dynamodb.DynamoDbClientProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {
    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {
        return new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_EAST_2));
    }
}
