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
@Table(name = "tweet_comment")
public class TweetComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer sysGenId;

    @Column(name = "USER_ID", updatable = false, nullable = false)
    private Integer userId;

    @Column(name = "TWEET_ID", updatable = false, nullable = false)
    private Integer tweetId;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATEDDATETIME", updatable = false, nullable = false)
    private Date createdDateTime;



}
