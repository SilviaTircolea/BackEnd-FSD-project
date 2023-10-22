package com.twitter.app.model.response;

import lombok.Data;

import java.util.List;

@Data
public class FeedDTO {

    private List<FeedTweet> tweets;
}
