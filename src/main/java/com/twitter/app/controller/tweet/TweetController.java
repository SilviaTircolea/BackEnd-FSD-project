package com.twitter.app.controller.tweet;

import com.twitter.app.model.request.Tweet;
import com.twitter.app.model.response.Response;
import com.twitter.app.service.tweet.TweetService;
import com.twitter.app.utill.CommonVarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;


@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("")
    public @ResponseBody
    ResponseEntity createTweet(@RequestBody Tweet tweet, Principal principal) {
        HttpStatus httpStatus;
        Response response = new Response();
        Integer tweetId = null;
        try {
            tweetId = tweetService.createTweet(tweet.getTweetDescription(), principal.getName());

            if (tweetId > 0) {
                response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
                response.setResponseMessage("Tweet Created Successfully!");
                response.setResponseData(tweetId);
                httpStatus = HttpStatus.OK;
            } else if(tweetId == -1){
                response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
                response.setResponseMessage("Tweet should be less than 100 characters");
                httpStatus = HttpStatus.BAD_REQUEST;
            }else{
                response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
                response.setResponseMessage("Duplicate tweet. Please try again.");
                httpStatus = HttpStatus.BAD_REQUEST;
            }

        } catch (SQLException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Error while doing the operation.");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/{id}/like")
    public @ResponseBody
    ResponseEntity likeTweet(Principal principal, @PathVariable String id) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            tweetService.likeTweet(Integer.valueOf(id), principal.getName());
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("Operation Success!");
            httpStatus = HttpStatus.OK;

        } catch (SQLException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Error while doing the operation.");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/{id}/comment")
    public @ResponseBody
    ResponseEntity commentTweet(Principal principal, @PathVariable String id, @RequestBody Tweet tweet) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            tweetService.commentTweet(Integer.valueOf(id), tweet.getComment(), principal.getName());
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("Operation Success!");
            httpStatus = HttpStatus.OK;

        } catch (SQLException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Error while doing the operation.");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/{id}/share")
    public @ResponseBody
    ResponseEntity shareTweet(Principal principal, @PathVariable String id) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            tweetService.shareTweet(Integer.valueOf(id), principal.getName());
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("Operation Success!");
            httpStatus = HttpStatus.OK;

        } catch (SQLException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Error while doing the operation.");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
