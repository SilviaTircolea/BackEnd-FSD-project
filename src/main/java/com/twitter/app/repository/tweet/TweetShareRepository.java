package com.twitter.app.repository.tweet;

import com.twitter.app.model.tweet.TweetShare;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TweetShareRepository extends JpaRepository<TweetShare, Integer> {

    long countByTweetId(Integer id);


}