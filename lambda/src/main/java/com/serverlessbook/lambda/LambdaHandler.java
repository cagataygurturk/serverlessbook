package com.serverlessbook.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.ParameterizedType;

public abstract class LambdaHandler<I, O> implements RequestStreamHandler {

    final ObjectMapper mapper;

    protected LambdaHandler() {
        mapper = new ObjectMapper();
    }

    @SuppressWarnings("unchecked")
    private Class<I> getInputType() {
        return (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    }

    public abstract O handleRequest(I input, Context context);
}
