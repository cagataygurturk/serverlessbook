package com.serverlessbook.services.user.repository;


import com.serverlessbook.services.user.domain.User;

import java.util.Optional;

public class UserRepositoryDynamoDB implements UserRepository {

    @Override
    public Optional<User> getUserByToken(String token) {
        return Optional.empty();
    }

}
