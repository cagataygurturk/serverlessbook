package com.serverlessbook.lambda;

import com.amazonaws.services.lambda.runtime.Context;

public class LambdaHandlerTest {

    protected static class TestInput {
        public String value;
    }

    protected static class TestOutput {
        public String value;
    }

    protected static class TestLambdaHandler extends LambdaHandler<TestInput, TestOutput> {
        @Override
        public TestOutput handleRequest(TestInput input, Context context) {
            TestOutput testOutput = new TestOutput();
            testOutput.value = input.value;
            return testOutput;
        }
    }
}
