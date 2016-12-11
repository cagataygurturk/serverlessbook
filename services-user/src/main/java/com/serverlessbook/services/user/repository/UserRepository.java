package com.serverlessbook.services.user.repository;

import com.serverlessbook.services.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByToken(String token);
}