package com.serverlessbook.services.user.exception;


public abstract class UserRegistrationException extends Exception {
    private static final long serialVersionUID = -7628860081079461234L;

    protected UserRegistrationException(String message) {
        super(message);
    }
}
