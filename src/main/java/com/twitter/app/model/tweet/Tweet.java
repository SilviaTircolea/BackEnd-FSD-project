package com.twitter.app.model.tweet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.twitter.app.model.user.UserDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer sysGenId;

    @Column(name = "USER_ID", updatable = false, nullable = false)
    private Integer userId;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATEDDATETIME", updatable = false, nullable = false)
    private Date createdDateTime;



}
