package com.twitter.app.controller.feed;

import com.twitter.app.model.response.Response;
import com.twitter.app.service.feed.FeedService;
import com.twitter.app.utill.CommonVarList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.SQLException;

@RestController
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping("")
    public @ResponseBody
    ResponseEntity getFeed(Principal principal, Pageable pageable) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("Data fetched Successfully!");
            response.setResponseData(feedService.getFeedData(principal.getName(),pageable));
            httpStatus = HttpStatus.OK;

        } catch (SQLException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Error while doing the operation.");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
