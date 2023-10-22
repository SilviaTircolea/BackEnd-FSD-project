package com.twitter.app.repository.tweet;

import com.twitter.app.model.tweet.TweetComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TweetCommentRepository extends JpaRepository<TweetComment, Integer> {

    long countByTweetId(Integer id);
}