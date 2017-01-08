package com.serverlessbook.services.user.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.serverlessbook.services.user.domain.User;

import javax.inject.Inject;
import java.util.Optional;

public class UserRepositoryDynamoDB implements UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public UserRepositoryDynamoDB(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public Optional<User> getUserByToken(String token) {
        return Optional.empty();
    }

}
