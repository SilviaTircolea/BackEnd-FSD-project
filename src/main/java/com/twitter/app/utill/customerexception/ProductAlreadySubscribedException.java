package com.twitter.app.utill.customerexception;

public class ProductAlreadySubscribedException extends Exception {

    public ProductAlreadySubscribedException(String errorMessage) {
        super(errorMessage);
    }

}
