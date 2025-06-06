package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.dto.UserDTO;
import com.vuhoang.hueplan.entity.UserEntity;

import java.util.List;

public interface I_User {

     UserDTO getUser (int userID);
     List<UserDTO> getAllUser ();
     int updateUser (UserDTO user);
     boolean deleteUser (int userID);
}
