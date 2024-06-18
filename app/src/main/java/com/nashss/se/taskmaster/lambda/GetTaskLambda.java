package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.GetTaskRequest;
import com.nashss.se.taskmaster.activity.result.GetTaskResult;
import com.nashss.se.taskmaster.enums.Status;

public class GetTaskLambda 
        extends LambdaActivityRunner<GetTaskRequest, GetTaskResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetTaskRequest>, LambdaResponse> {
    
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetTaskRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetTaskRequest unauthenticatedRequest = input.fromPath(path -> 
                GetTaskRequest.builder()
                        .withStatus(Enum.valueOf(Status.class, path.get("status")))
                        .build()
                );

                return input.fromUserClaims(claims -> 
                    GetTaskRequest.builder()
                            .withUserId(claims.get("email"))
                            .withStatus(unauthenticatedRequest.getStatus())
                            .build());
            }, 
            (request, serviceComponent) ->
                    serviceComponent.provideGetTaskActivity().handleRequest(request)
        );
    }
}
