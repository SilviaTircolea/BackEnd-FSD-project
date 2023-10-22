package com.twitter.app.service.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.app.model.user.User;
import com.twitter.app.model.user.UserCommon;
import com.twitter.app.utill.customerexception.EmailAlreadyExistingException;
import com.twitter.app.utill.customerexception.UserIdAlreadyExistingException;
import com.twitter.app.utill.customerexception.UserInactiveException;
import org.hibernate.HibernateException;

import javax.mail.MessagingException;
import java.sql.SQLException;

public interface UserService {


    User getUserByUserId(String UserId) throws HibernateException;

    void registration(User user) throws UserIdAlreadyExistingException, EmailAlreadyExistingException, HibernateException, JsonProcessingException, MessagingException;

    UserCommon signIn(User user) throws HibernateException, UserInactiveException, SQLException, JsonProcessingException;

    void updateUser( User user,String id) throws HibernateException, JsonProcessingException;

}