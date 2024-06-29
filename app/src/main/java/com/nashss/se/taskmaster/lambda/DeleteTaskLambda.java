package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.DeleteTaskRequest;
import com.nashss.se.taskmaster.activity.result.DeleteTaskResult;

public class DeleteTaskLambda
        extends LambdaActivityRunner<DeleteTaskRequest, DeleteTaskResult>
        implements RequestHandler<LambdaRequest<DeleteTaskRequest>, LambdaResponse> { 
    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteTaskRequest> input, Context context) {
        return super.runActivity(() -> {
                return input.fromPath(path ->
                        DeleteTaskRequest.builder()
                        .withUserId(path.get("userId"))
                        .withId(path.get("taskId"))
                        .build()
                );
        },
        (request, serviceComponent) -> 
                serviceComponent.provideDeleteTaskActivity().handleRequest(request)
        );
    }
}
