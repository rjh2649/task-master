package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.UpdateTaskRequest;
import com.nashss.se.taskmaster.activity.result.UpdateTaskResult;

public class UpdateTaskLambda
        extends LambdaActivityRunner<UpdateTaskRequest, UpdateTaskResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateTaskRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateTaskRequest> input, Context context) {
        return super.runActivity(() -> {
            UpdateTaskRequest unauthenticatedRequest = input.fromBody(UpdateTaskRequest.class);

            return input.fromUserClaims(claims -> 
                UpdateTaskRequest.builder()
                        .withUserId(claims.get("email"))
                        .withId(unauthenticatedRequest.getId())
                        .withDesc(unauthenticatedRequest.getDesc())
                        .withPriority(unauthenticatedRequest.getPriority())
                        .withDoBy(unauthenticatedRequest.getDoBy())
                        .withStatus(unauthenticatedRequest.getStatus())
                        .withPoints(unauthenticatedRequest.getPoints())
                        .build());
        }, 
        
        (request, serviceComponent) -> serviceComponent.provideUpdateTaskActivity().handleRequest(request));
    }
}
