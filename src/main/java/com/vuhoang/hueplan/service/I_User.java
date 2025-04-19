package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.entity.UserEntity;

import java.util.List;

public interface I_User {

     UserEntity getUser (int userID);
     List<UserEntity> getAllUser ();
     UserEntity addUser (UserEntity user);
     int updateUser (UserEntity user);
     boolean deleteUser (int userID);
}
