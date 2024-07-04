package com.nashss.se.taskmaster.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.GetRewardsRequest;
import com.nashss.se.taskmaster.activity.result.GetRewardsResult;

public class GetRewardsLambda 
        extends LambdaActivityRunner<GetRewardsRequest, GetRewardsResult> 
        implements RequestHandler<LambdaRequest<GetRewardsRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetRewardsRequest> input, Context context) {
        return super.runActivity(() -> {
            return input.fromPath(path -> GetRewardsRequest.builder()
                            .withUserId(path.get("userId"))
                            .build());   
        }, 
        (request, serviceComponent) -> serviceComponent.provideGetRewardsActivity().handleRequest(request));
    }
}
