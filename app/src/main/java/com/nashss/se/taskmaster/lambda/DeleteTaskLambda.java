package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.DeleteTaskRequest;
import com.nashss.se.taskmaster.activity.result.DeleteTaskResult;

public class DeleteTaskLambda
        extends LambdaActivityRunner<DeleteTaskRequest, DeleteTaskResult>
        implements RequestHandler<AuthenticatedLambdaRequest<DeleteTaskRequest>, LambdaResponse> { 
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteTaskRequest> input, Context context) {
        return super.runActivity(() -> {
            DeleteTaskRequest unauthenticatedRequest = input.fromPath(path ->
            DeleteTaskRequest.builder()
                    .withId(path.get("taskId"))
                    .build()
            );
            
            return input.fromUserClaims(claims -> 
                    DeleteTaskRequest.builder()
                            .withUserId(claims.get("email"))
                            .withId(unauthenticatedRequest.getId())
                            .build());
        },
        (request, serviceComponent) -> 
                serviceComponent.provideDeleteTaskActivity().handleRequest(request)
        );
    }
}
