package com.twitter.app.utill.customerexception;

public class SubscriptionNotFound extends Exception {

    public SubscriptionNotFound(String errorMessage) {
        super(errorMessage);
    }

}
