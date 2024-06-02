package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.CreateTaskRequest;
import com.nashss.se.taskmaster.activity.result.CreateTaskResult;

public class CreateTaskLambda 
        extends LambdaActivityRunner<CreateTaskRequest, CreateTaskResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateTaskRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateTaskRequest> input, Context context) {
        return super.runActivity(() -> {
            CreateTaskRequest unauthenticatedRequest = input.fromBody(CreateTaskRequest.class);
            return input.fromUserClaims(claims -> 
                    CreateTaskRequest.builder()
                    .withId(unauthenticatedRequest.getId())
                    .withDescription(unauthenticatedRequest.getDescription())
                    .withPriority(unauthenticatedRequest.getPriority())
                    .withDoBy(unauthenticatedRequest.getDoBy())
                    .withStatus(unauthenticatedRequest.getStatus())
                    .withPoints(unauthenticatedRequest.getPoints())
                    .build());
        },
        (request, serviceComponent) -> 
                serviceComponent.provideCreateTaskActivity().handleRequest(request)
        );
    }
}
