package com.twitter.app.service.feed;


import com.twitter.app.model.response.FeedTweet;
import com.twitter.app.model.tweet.Tweet;
import com.twitter.app.model.user.User;
import com.twitter.app.repository.tweet.TweetCommentRepository;
import com.twitter.app.repository.tweet.TweetLikeRepository;
import com.twitter.app.repository.tweet.TweetRepository;
import com.twitter.app.repository.tweet.TweetShareRepository;
import com.twitter.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedServiceImpl implements FeedService{

    private final UserRepository userRepository;
    private final TweetRepository tweetRepository;
    private final TweetLikeRepository tweetLikeRepository;
    private final TweetCommentRepository tweetCommentRepository;
    private final TweetShareRepository tweetShareRepository;

    @Autowired
    public FeedServiceImpl(UserRepository userRepository, TweetRepository tweetRepository, TweetLikeRepository tweetLikeRepository, TweetCommentRepository tweetCommentRepository, TweetShareRepository tweetShareRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.tweetLikeRepository = tweetLikeRepository;
        this.tweetCommentRepository = tweetCommentRepository;
        this.tweetShareRepository = tweetShareRepository;
    }

    @Override
    public List<FeedTweet> getFeedData(String userId, Pageable pageable) throws SQLException {

        List<FeedTweet> listData = new ArrayList<>();
        User userByUserId = userRepository.getUserByUserId(userId);

        List<Tweet> data = tweetRepository.getAllByUserId(userByUserId.getSysGenId(),pageable);

        for(Tweet tweet : data){
            FeedTweet feedTweet = new FeedTweet();
            feedTweet.setId(tweet.getSysGenId());
            feedTweet.setDescription(tweet.getDescription());

            feedTweet.setLikesCount(tweetLikeRepository.countByTweetId(tweet.getSysGenId()));
            feedTweet.setCommentCount(tweetCommentRepository.countByTweetId(tweet.getSysGenId()));
            feedTweet.setReShareCount(tweetShareRepository.countByTweetId(tweet.getSysGenId()));

            listData.add(feedTweet);
        }



        return listData;
    }
}
