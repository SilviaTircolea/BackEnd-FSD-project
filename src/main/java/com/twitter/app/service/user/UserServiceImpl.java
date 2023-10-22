package com.twitter.app.service.user;

/*
 * @Author            : ROSHEN DILSHAN
 * @File Name         : UserServiceImpl
 * @Created Date Time : 6/23/2019 2:18 PM
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.app.model.user.User;
import com.twitter.app.model.user.UserCommon;
import com.twitter.app.repository.user.UserRepository;
import com.twitter.app.service.common.CommonService;
import com.twitter.app.service.jwtutil.JwtUtil;
import com.twitter.app.utill.Common;
import com.twitter.app.utill.customerexception.EmailAlreadyExistingException;
import com.twitter.app.utill.customerexception.UserIdAlreadyExistingException;
import com.twitter.app.utill.customerexception.UserInactiveException;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.sql.SQLException;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CommonService commonService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,

                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           CommonService commonService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.commonService = commonService;
    }

    @Override
    public User getUserByUserId(String UserId) throws HibernateException {
        User user = userRepository.getUserBySysGenId(Integer.parseInt(UserId));
        return user;
    }

    @Override
    @Transactional
    public void registration(User user) throws UserIdAlreadyExistingException, EmailAlreadyExistingException, HibernateException, JsonProcessingException, MessagingException {
        user.setUserId(user.getUserId().toLowerCase());

        if(user.getPassword().trim().length() > 8 && user.getPassword().trim().length() < 12) {

            if (userRepository.countByUserId(user.getUserId()) > 0) {
                throw new UserIdAlreadyExistingException("User ID already exist");
            } else {
                user.setStatus(3);
                String password = user.getPassword();
                user.setPassword(passwordEncoder.encode(password));
                user.setFollowers(0);
                user.setTweets(0);
                userRepository.save(user);
            }
        }else{
            throw new UserIdAlreadyExistingException("Password should lass than 12 characters and greater than 8 characters.");
        }
    }

    @Override
    public UserCommon signIn(User user) throws HibernateException, UserInactiveException, SQLException, JsonProcessingException {
        User systemUser = userRepository.getUserByUserId(user.getUserId());
        if (systemUser.getStatus() == 4) {
            throw new UserInactiveException("User inactive, please contact administrator");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
        String token = jwtUtil.generateToken(systemUser);
        UserCommon userCommon = new UserCommon();
        systemUser.setJwt(token);
        systemUser.setLastUpdatedUser(user.getUserId());
        userRepository.save(systemUser);
        userCommon.setUser(systemUser);
        return userCommon;
    }
//
    @Override
    @Transactional
    public void updateUser(User user,String id) throws HibernateException, JsonProcessingException {
        User existingUser = userRepository.getUserBySysGenId(Integer.parseInt(id.trim()));
        User data = user;

        user = existingUser;
        user.setFirstName(data.getFirstName().trim());
        user.setLastName(data.getLastName().trim());
        user.setProfilePicture(data.getProfilePicture().trim());
        user.setBio(data.getBio().trim());
        user.setLastUpdatedUser(existingUser.getUserId());
        userRepository.save(user);
    }


}