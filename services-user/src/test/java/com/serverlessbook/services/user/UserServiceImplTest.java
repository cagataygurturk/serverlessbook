package com.serverlessbook.services.user;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.serverlessbook.repository.DynamoDBMapperWithCustomTableName;
import com.serverlessbook.services.user.repository.UserRepositoryDynamoDB;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class UserServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private UserService getUserService() {
        return new UserServiceImpl(new UserRepositoryDynamoDB(new DynamoDBMapperWithCustomTableName(new AmazonDynamoDBClient())));
    }

}