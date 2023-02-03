package com.example.autogaragebe.exeption;


public class NotAuthorizedException extends RuntimeException {


    public NotAuthorizedException(String message) {
        super(message);
    }
    public NotAuthorizedException() {
        super("You are not authorized.");
    }
}