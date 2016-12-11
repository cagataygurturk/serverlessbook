package com.serverlessbook.services.user;


import com.serverlessbook.services.user.domain.User;

public interface UserService {
    User getUserByToken(String token) throws UserNotFoundException;
}
