package com.twitter.app.service.user;


import com.twitter.app.model.user.User;
import com.twitter.app.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserId(username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), new ArrayList<>());
        return userDetails;
    }
}