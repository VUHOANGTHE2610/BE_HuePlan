package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.FavoriteLocationDTO;
import com.vuhoang.hueplan.entity.FavoriteLocationEntity;
import com.vuhoang.hueplan.entity.LocationEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class FavoriteLocationMapper {

    public FavoriteLocationDTO toDTO(FavoriteLocationEntity entity) {
        FavoriteLocationDTO dto = new FavoriteLocationDTO();
        dto.setFavorite_ID(entity.getFavorite_ID());
        dto.setUser_ID(entity.getUser().getUser_ID());
        dto.setLocation_ID(entity.getLocation().getLocation_ID());
        return dto;
    }

    public FavoriteLocationEntity toEntity(FavoriteLocationDTO dto) {
        FavoriteLocationEntity entity = new FavoriteLocationEntity();
        entity.setFavorite_ID(dto.getFavorite_ID());

        // Khởi tạo UserEntity
        UserEntity user = new UserEntity();
        user.setUser_ID(dto.getUser_ID());
        entity.setUser(user);

        // Khởi tạo LocationEntity
        LocationEntity location = new LocationEntity();
        location.setLocation_ID(dto.getLocation_ID());
        entity.setLocation(location);

        return entity;
    }
}
