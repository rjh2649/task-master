package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.result.GetTaskResult;

public class GetTaskLambda 
        extends LambdaActivityRunner<Void, GetTaskResult>
        implements RequestHandler<AuthenticatedLambdaRequest<Void>, LambdaResponse> {
    
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<Void> input, Context context) {
        return super.runActivity(() -> {
            return null;
        }, (request, serviceComponent) ->
                serviceComponent.provideGetTaskActivity().handleRequest());
    }
}
