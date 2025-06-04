package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.LocationDTO;
import com.vuhoang.hueplan.entity.LocationEntity;
import com.vuhoang.hueplan.entity.LocationPhotoEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LocationMapper {
    @Autowired
    private UserRepository userRepository;

    public LocationEntity toEntity(LocationDTO dto) {
        LocationEntity entity = new LocationEntity();
        entity.setLocation_ID(dto.getLocation_ID());
        entity.setLocation_Name(dto.getLocation_Name());
        entity.setLocation_Description(dto.getLocation_Description());
        entity.setLocation_Address(dto.getLocation_Address());
        entity.setLocation_Cost(dto.getLocation_Cost());
        entity.setStatus(dto.isStatus());
        entity.setCreateBy(dto.getCreateBy());

        UserEntity user = userRepository.findById(dto.getUser_ID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + dto.getUser_ID()));
        entity.setUser(user);

        if (dto.getLocation_Photos() != null) {
            entity.setPhotos(dto.getLocation_Photos().stream()
                    .map(photoDTO -> {
                        LocationPhotoEntity photo = new LocationPhotoEntity();
                        photo.setPhoto_ID(photoDTO.getPhotoId() != null ? photoDTO.getPhotoId() : 0);
                        photo.setPhoto_URL(photoDTO.getPhotoUrl());
                        photo.setLocation(entity);
                        return photo;
                    })
                    .collect(Collectors.toList()));
        }
        return entity;
    }

    public LocationDTO toDTO(LocationEntity entity) {
        LocationDTO dto = new LocationDTO();
        dto.setLocation_ID(entity.getLocation_ID());
        dto.setLocation_Name(entity.getLocation_Name());
        dto.setLocation_Description(entity.getLocation_Description());
        dto.setLocation_Address(entity.getLocation_Address());
        dto.setLocation_Cost(entity.getLocation_Cost());
        dto.setStatus(entity.isStatus());
        dto.setCreateBy(entity.getCreateBy());
        dto.setUser_ID(entity.getUser().getUser_ID());

        if (entity.getPhotos() != null) {
            dto.setLocation_Photos(entity.getPhotos().stream()
                    .map(photo -> new LocationDTO.PhotoDTO(photo.getPhoto_ID(), photo.getPhoto_URL()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}