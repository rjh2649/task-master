package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.CreateRewardRequest;
import com.nashss.se.taskmaster.activity.result.CreateRewardResult;

public class CreateRewardLambda 
       extends LambdaActivityRunner<CreateRewardRequest, CreateRewardResult>
       implements RequestHandler<LambdaRequest<CreateRewardRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateRewardRequest> input, Context context) {
        return super.runActivity(
            () -> {
                return input.fromBody(CreateRewardRequest.class);
        }, 
        (request, serviceComponent) -> 
                serviceComponent.provideCreateRewardActivity().handleRequest(request)
        );
    }
}
