package com.serverlessbook.lambda.authorizer;

import com.amazonaws.services.lambda.runtime.Context;
import com.serverlessbook.lambda.LambdaHandler;
import com.serverlessbook.lambda.authorizer.models.AuthorizationInput;
import com.serverlessbook.lambda.authorizer.models.AuthorizationOutput;
import com.serverlessbook.lambda.authorizer.models.policy.PolicyDocument;
import com.serverlessbook.lambda.authorizer.models.policy.PolicyStatement;

public class Handler extends LambdaHandler<AuthorizationInput, AuthorizationOutput> {

    @Override
    public AuthorizationOutput handleRequest(AuthorizationInput input, Context context) {
        final String validToken = "serverless";
        final String authenticationToken = input.getAuthorizationToken();
        final PolicyDocument policyDocument = new PolicyDocument();
        final PolicyStatement.Effect policyEffect = "serverless".equals(authenticationToken) ? PolicyStatement.Effect.ALLOW : PolicyStatement.Effect.DENY;
        policyDocument.withPolicyStatement(new PolicyStatement("execute-api:Invoke",
                policyEffect, input.getMethodArn()));
        return new AuthorizationOutput("1234", policyDocument);
    }
}
