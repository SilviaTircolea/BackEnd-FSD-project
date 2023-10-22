package com.twitter.app.model.response;

import lombok.Data;

@Data
public class FeedTweet {

    private Integer id;
    private String description;
    private long likesCount;
    private long commentCount;
    private long reShareCount;
}
