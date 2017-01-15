package com.serverlessbook.services.user.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.serverlessbook.services.user.domain.Token;
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
        Token foundTokenInDynamoDB = dynamoDBMapper.load(Token.class, token);
        if (foundTokenInDynamoDB != null) {
            // Token found in DynamoDb, try to fetch the user in a second query
            return Optional.ofNullable(dynamoDBMapper.load(User.class, foundTokenInDynamoDB.getUserId()));
        }
        // Token not found, return empty.
        return Optional.empty();
    }

    @Override
    public void saveUser(User user) {
        dynamoDBMapper.save(user);
    }
}
