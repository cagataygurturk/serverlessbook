package com.serverlessbook.lambda.authorizer;

import com.google.inject.AbstractModule;
import com.serverlessbook.services.user.UserService;
import com.serverlessbook.services.user.UserServiceImpl;

public class DependencyInjectionModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class);
    }
}
