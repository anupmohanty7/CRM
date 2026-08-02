package com.crm.exception;

public class FollowUpNotFoundException extends RuntimeException {

    public FollowUpNotFoundException(String message) {
        super(message);
    }
}