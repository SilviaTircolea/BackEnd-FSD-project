package com.twitter.app.repository.tweet;

import com.twitter.app.model.tweet.TweetLike;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TweetLikeRepository extends JpaRepository<TweetLike, Integer> {

    long countByTweetId(Integer id);
}