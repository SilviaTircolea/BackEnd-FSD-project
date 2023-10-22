package com.twitter.app.service.feed;

import com.twitter.app.model.response.FeedTweet;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.util.List;

public interface FeedService {

    List<FeedTweet> getFeedData(String userId, Pageable pageable) throws SQLException;
}
