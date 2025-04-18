package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.BusinessDTO;
import com.vuhoang.hueplan.entity.BusinessEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {

    public BusinessEntity toEntity(BusinessDTO dto, UserEntity user) {
        BusinessEntity entity = new BusinessEntity();
        entity.setBusiness_ID(dto.getBusiness_ID());
        entity.setBusiness_Photo(dto.getBusiness_Photo());
        entity.setBusiness_Name(dto.getBusiness_Name());
        entity.setBusiness_Location(dto.getBusiness_Location());
        entity.setBusiness_Cost(dto.getBusiness_Cost());
        entity.setBusiness_phone(dto.getBusiness_phone());
        entity.setBusiness_Description(dto.getBusiness_Description());
        entity.setUser(user);
        return entity;
    }

    public  BusinessDTO toDTO(BusinessEntity entity) {
        BusinessDTO dto = new BusinessDTO();
        dto.setBusiness_ID(entity.getBusiness_ID());
        dto.setBusiness_Photo(entity.getBusiness_Photo());
        dto.setBusiness_Name(entity.getBusiness_Name());
        dto.setBusiness_Location(entity.getBusiness_Location());
        dto.setBusiness_Cost(entity.getBusiness_Cost());
        dto.setBusiness_phone(entity.getBusiness_phone());
        dto.setBusiness_Description(entity.getBusiness_Description());
        dto.setUser_ID(entity.getUser().getUser_ID());
        return dto;
    }
}
