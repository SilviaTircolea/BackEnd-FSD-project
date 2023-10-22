package com.twitter.app.service.tweet;

import javassist.bytecode.DuplicateMemberException;

import java.sql.SQLException;

public interface TweetService {

    Integer createTweet(String tweetDescription,String userId) throws SQLException;

    void likeTweet(Integer tweetId,String userId) throws SQLException;

    void commentTweet(Integer tweetId,String description, String userId) throws SQLException;

    void shareTweet(Integer tweetId,String userId) throws SQLException;
}
