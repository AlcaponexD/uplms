package com.uplms.project.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("User found");
    }
}
