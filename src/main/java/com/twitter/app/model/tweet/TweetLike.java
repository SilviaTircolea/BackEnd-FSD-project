package com.twitter.app.model.tweet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tweet_like")
public class TweetLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer sysGenId;

    @Column(name = "TWEET_ID", updatable = false, nullable = false)
    private Integer tweetId;

    @Column(name = "USER_ID")
    private Integer userId;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATEDDATETIME", updatable = false, nullable = false)
    private Date createdDateTime;



}
