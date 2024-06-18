package com.nashss.se.taskmaster.lambda;

import static org.mockito.Mockito.inOrder;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.taskmaster.activity.request.CreateTaskRequest;
import com.nashss.se.taskmaster.activity.result.CreateTaskResult;
import com.nashss.se.taskmaster.enums.Status;

public class CreateTaskLambda 
        extends LambdaActivityRunner<CreateTaskRequest, CreateTaskResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateTaskRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateTaskRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateTaskRequest unauthenticatedRequest = input.fromBody(CreateTaskRequest.class);

                String id = unauthenticatedRequest.getId();
                Status status = unauthenticatedRequest.getStatus();
                Integer points = unauthenticatedRequest.getPoints();

                return input.fromUserClaims(claims -> CreateTaskRequest.builder()
                            .withUserId(claims.get("email"))
                            .withDesc(unauthenticatedRequest.getDesc())
                            .withId(id)
                            .withPriority(unauthenticatedRequest.getPriority())
                            .withDoBy(unauthenticatedRequest.getDoBy())
                            .withStatus(status)
                            .withPoints(points)
                            .build()
                );
        },
        (request, serviceComponent) -> 
                serviceComponent.provideCreateTaskActivity().handleRequest(request)
        );
    }
}
