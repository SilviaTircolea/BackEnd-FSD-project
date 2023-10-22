package com.twitter.app.utill.customerexception;

public class UserIdAlreadyExistingException extends Exception {

    public UserIdAlreadyExistingException(String errorMessage) {
        super(errorMessage);
    }

}
