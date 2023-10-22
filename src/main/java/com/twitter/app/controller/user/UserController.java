package com.twitter.app.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.app.model.response.Response;
import com.twitter.app.model.user.User;
import com.twitter.app.model.user.UserCommon;
import com.twitter.app.service.common.CommonService;
import com.twitter.app.service.jwtutil.JwtUtil;
import com.twitter.app.service.user.UserService;
import com.twitter.app.utill.CommonVarList;
import com.twitter.app.utill.customerexception.EmailAlreadyExistingException;
import com.twitter.app.utill.customerexception.UserIdAlreadyExistingException;
import com.twitter.app.utill.customerexception.UserInactiveException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.sql.SQLException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager,
                          CommonService commonService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public @ResponseBody
    ResponseEntity signUp(@RequestBody User user) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            String userId = user.getUserId();
            user.setCreatedUser(userId);
            user.setLastUpdatedUser(userId);
            userService.registration(user);
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("User registration success");
            httpStatus = HttpStatus.OK;
        } catch (UserIdAlreadyExistingException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (EmailAlreadyExistingException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (HibernateException | JsonProcessingException | MessagingException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("User registration failed");
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/login")
    public @ResponseBody
    ResponseEntity signIn(@RequestBody User user) {
        HttpStatus httpStatus;
        Response response = new Response();
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("User successfully sign in");
            UserCommon userCommon = userService.signIn(user);
            response.setResponseData(userCommon);
            responseHeaders.set("jwt_token", userCommon.getUser().getJwt());
            httpStatus = HttpStatus.OK;
        } catch (UserInactiveException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (BadCredentialsException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Invalid username password");
            httpStatus = HttpStatus.NOT_ACCEPTABLE;
        } catch (SQLException | HibernateException | JsonProcessingException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("Internal server error");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).headers(responseHeaders).body(response);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity getUserByUserId(@PathVariable String id) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            User user = userService.getUserByUserId(id);
            if (user != null) {
                response.setResponseData(user);
                response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
                response.setResponseMessage("User data fetched Successfully!");
                httpStatus = HttpStatus.OK;
            } else {
                response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
                response.setResponseMessage("User not found");
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (HibernateException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("User selection failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity updateUser(@RequestBody User user, @PathVariable String id) {
        HttpStatus httpStatus;
        Response response = new Response();
        try {
            userService.updateUser(user, id);
            response.setResponseCode(CommonVarList.RESPONSE_CODE_SUCCESS);
            response.setResponseMessage("User updated Successfully!");
            httpStatus = HttpStatus.OK;
        } catch (HibernateException | JsonProcessingException e) {
            response.setResponseCode(CommonVarList.RESPONSE_CODE_FAILURE);
            response.setResponseMessage("User update failed");
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }

}
