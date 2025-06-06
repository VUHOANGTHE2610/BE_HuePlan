package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.FavoriteLocationDTO;
import com.vuhoang.hueplan.dto.UserDTO;
import com.vuhoang.hueplan.entity.FavoriteLocationEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private FavoriteLocationMapper favoriteLocationMapper;


    public UserDTO toDTO (UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_ID(userEntity.getUser_ID());
        userDTO.setUserEmail(userEntity.getUserEmail());
        userDTO.setUserPassword(userEntity.getUser_Password());
        userDTO.setUserName(userEntity.getUser_Name());
        userDTO.setRole(userEntity.getRole());
//        userDTO.setStatus(userEntity.isStatus());

        // Ánh xạ danh sách favoriteLocations
        if (userEntity.getFavoriteLocations() != null) {
            List<FavoriteLocationDTO> favoriteLocationDTOs = userEntity.getFavoriteLocations()
                    .stream()
                    .map(favoriteLocationMapper::toDTO)
                    .collect(Collectors.toList());
            userDTO.setFavoriteLocations(favoriteLocationDTOs);
        }
        return userDTO;
    }

    public UserEntity toEntity (UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_ID(userDTO.getUser_ID());
        userEntity.setUserEmail(userDTO.getUserEmail());
        userEntity.setUser_Password(userDTO.getUserPassword());
        userEntity.setUser_Name(userDTO.getUserName());
        userEntity.setRole(userDTO.getRole());
//        userEntity.setStatus(userDTO.isStatus());

        // Ánh xạ danh sách favoriteLocations
        if (userDTO.getFavoriteLocations() != null) {
            List<FavoriteLocationEntity> favoriteLocationEntities = userDTO.getFavoriteLocations()
                    .stream()
                    .map(favoriteLocationMapper::toEntity)
                    .collect(Collectors.toList());
            userEntity.setFavoriteLocations(favoriteLocationEntities);
        }
        return userEntity;
    }
}