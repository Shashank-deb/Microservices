package com.shashank.userservice.services;

import com.shashank.userservice.entity.User;

import java.util.List;

public interface UserService {


    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user of given userid
    User getUser(String userId);




}
