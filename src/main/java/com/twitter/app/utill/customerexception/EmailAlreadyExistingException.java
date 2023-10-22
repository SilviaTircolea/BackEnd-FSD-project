package com.twitter.app.utill.customerexception;

public class EmailAlreadyExistingException extends Exception {

    public EmailAlreadyExistingException(String errorMessage) {
        super(errorMessage);
    }

}
