package com.twitter.app.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(using = UserDeserializer.class)
@JsonIgnoreProperties(value = {"password", "jwt"}, allowSetters = true)
@Table(name = "systemuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer sysGenId;

    @NotBlank(message = "User id  is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",message = "User Id format incorrect")
    @Column(name = "USERID", updatable = false, nullable = false)
    private String userId;

    @NotBlank(message = "Password is mandatory")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "First Name is mandatory")
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "LASTNAME")
    private String lastName;

    @JsonProperty("jwt")
    private String jwt;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "PROFILE_PIC")
    private String profilePicture;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "FOLLOWERS")
    private Integer followers;

    @Column(name = "TWEETS")
    private Integer tweets;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATEDDATETIME", updatable = false, nullable = false)
    private Date createdDateTime;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "LASTUPDATEDDATETIME", nullable = false)
    private Date lastUpdatedDateTime;

    @JsonIgnore
    @Column(name = "CREATEDUSER", updatable = false, nullable = false)
    private String createdUser;

    @JsonIgnore
    @Column(name = "LASTUPDATEDUSER", nullable = false)
    private String lastUpdatedUser;

}
