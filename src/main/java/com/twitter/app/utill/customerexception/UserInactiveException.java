package com.twitter.app.utill.customerexception;

public class UserInactiveException extends Exception {

    public UserInactiveException(String message) {
        super(message);
    }
}
