package com.twitter.app.repository.tweet;

import com.twitter.app.model.tweet.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    Tweet getTweetByDescriptionAndUserId(String description,Integer userId);

    List<Tweet> getAllByUserId(Integer userId, Pageable pageable);
}