package com.twitter.app.model.request;

import lombok.Data;

@Data
public class UserRQDTO {

    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String createdUser;
    private String lastUpdatedUser;
}
