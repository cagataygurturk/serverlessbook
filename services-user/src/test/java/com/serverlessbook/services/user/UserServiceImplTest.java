package com.serverlessbook.services.user;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.serverlessbook.repository.DynamoDBMapperWithCustomTableName;
import com.serverlessbook.services.user.exception.AnotherUserWithSameUsernameExistsException;
import com.serverlessbook.services.user.repository.UserRepositoryDynamoDB;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;


public class UserServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private UserService getUserService() {
        return new UserServiceImpl(new UserRepositoryDynamoDB(new DynamoDBMapperWithCustomTableName(new AmazonDynamoDBClient())));
    }

    @Test
    public void failedUserRegistrationWithExistingUsernameTest() throws Exception {

        thrown.expect(AnotherUserWithSameUsernameExistsException.class);

        UserService userService = getUserService();

        final String username = UUID.randomUUID() + "test-username";

        userService.registerNewUser(username, UUID.randomUUID() + "@test.com");
        //Second call should fail
        userService.registerNewUser(username, UUID.randomUUID() + "@test.com");
    }

}