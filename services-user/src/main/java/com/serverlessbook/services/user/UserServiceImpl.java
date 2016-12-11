package com.serverlessbook.services.user;

import com.serverlessbook.services.user.domain.User;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserByToken(String token) throws UserNotFoundException {
        throw new UserNotFoundException();
    }

}
