package com.twitter.app.repository.user;

import com.twitter.app.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, String> {

    User getUserByUserId(String userId);

    User getUserBySysGenId(Integer userId);

    Integer countByUserId(String userId);

}