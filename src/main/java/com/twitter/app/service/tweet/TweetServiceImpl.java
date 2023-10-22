package com.twitter.app.service.tweet;

import com.twitter.app.model.tweet.Tweet;
import com.twitter.app.model.tweet.TweetComment;
import com.twitter.app.model.tweet.TweetLike;
import com.twitter.app.model.tweet.TweetShare;
import com.twitter.app.model.user.User;
import com.twitter.app.repository.tweet.TweetCommentRepository;
import com.twitter.app.repository.tweet.TweetLikeRepository;
import com.twitter.app.repository.tweet.TweetRepository;
import com.twitter.app.repository.tweet.TweetShareRepository;
import com.twitter.app.repository.user.UserRepository;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;
    private final TweetLikeRepository tweetLikeRepository;
    private final TweetCommentRepository tweetCommentRepository;
    private final TweetShareRepository tweetShareRepository;

    private final UserRepository userRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, TweetLikeRepository tweetLikeRepository, TweetCommentRepository tweetCommentRepository, TweetShareRepository tweetShareRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.tweetLikeRepository = tweetLikeRepository;
        this.tweetCommentRepository = tweetCommentRepository;
        this.tweetShareRepository = tweetShareRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Integer createTweet(String tweetDescription,String userId) throws SQLException {

        Integer tweetId = null;
        User userByUserId = userRepository.getUserByUserId(userId);
        if(tweetRepository.getTweetByDescriptionAndUserId(tweetDescription.trim(),userByUserId.getSysGenId()) == null) {
            Tweet tweet = new Tweet();
            tweet.setDescription(tweetDescription.trim());
            tweet.setUserId(userByUserId.getSysGenId());
            tweetId = tweetRepository.save(tweet).getSysGenId();
        }else{
            if(tweetDescription.trim().length() > 100){
                tweetId = -1;
            }
            tweetId = 0;
        }

        return tweetId;
    }

    @Override
    public void likeTweet(Integer tweetId,String userId) throws SQLException {
        User userByUserId = userRepository.getUserByUserId(userId);
        TweetLike tweetLike = new TweetLike();
        tweetLike.setTweetId(tweetId);
        tweetLike.setUserId(userByUserId.getSysGenId());
        tweetLikeRepository.save(tweetLike);
    }

    @Override
    public void commentTweet(Integer tweetId,String description ,String userId) throws SQLException {
        User userByUserId = userRepository.getUserByUserId(userId);
        TweetComment tweetComment = new TweetComment();
        tweetComment.setTweetId(tweetId);
        tweetComment.setUserId(userByUserId.getSysGenId());
        tweetComment.setDescription(description);
        tweetCommentRepository.save(tweetComment);
    }

    @Override
    public void shareTweet(Integer tweetId,String userId) throws SQLException {
        User userByUserId = userRepository.getUserByUserId(userId);
        TweetShare tweetShare = new TweetShare();
        tweetShare.setTweetId(tweetId);
        tweetShare.setUserId(userByUserId.getSysGenId());
        tweetShareRepository.save(tweetShare);
    }
}
