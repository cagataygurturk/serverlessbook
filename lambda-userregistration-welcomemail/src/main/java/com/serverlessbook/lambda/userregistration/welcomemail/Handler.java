package com.serverlessbook.lambda.userregistration.welcomemail;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.util.Objects;
import javax.inject.Inject;

import com.serverlessbook.services.user.domain.User;
import org.apache.log4j.Logger;

public class Handler implements RequestHandler<SNSEvent, Void> {

    private static final Injector INJECTOR = Guice.createInjector();

    private static final Logger LOGGER = Logger.getLogger(Handler.class);

    private AmazonSimpleEmailServiceClient simpleEmailServiceClient;

    @Inject
    public Handler setSimpleEmailServiceClient(
            AmazonSimpleEmailServiceClient simpleEmailServiceClient) {
        this.simpleEmailServiceClient = simpleEmailServiceClient;
        return this;
    }

    public Handler() {
        INJECTOR.injectMembers(this);
        Objects.nonNull(simpleEmailServiceClient);
    }

    private void sendEmail(final User user) {
        final String emailAddress = user.getEmail();
        Destination destination = new Destination().withToAddresses(emailAddress);

        Message message = new Message()
                .withBody(new Body().withText(new Content("Welcome to our forum!")))
                .withSubject(new Content("Welcome!"));

        try {
            LOGGER.debug("Sending welcome mail to " + emailAddress);
            simpleEmailServiceClient.sendEmail(new SendEmailRequest()
                    .withDestination(destination)
                    .withSource(System.getenv("SenderEmail"))
                    .withMessage(message)
            );
            LOGGER.debug("Sending welcome mail to " + emailAddress + " succeeded");
        } catch (Exception anyException) {
            LOGGER.error("Sending welcome mail to " + emailAddress + " failed: ", anyException);
        }

    }

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        input.getRecords().forEach(snsMessage -> {
            try {
                sendEmail(new ObjectMapper().readValue(snsMessage.getSNS().getMessage(), User.class));
            } catch (IOException anyException) {
                LOGGER.error("JSON could not be deserialized", anyException);
            }
        });
        return null;
    }
}
