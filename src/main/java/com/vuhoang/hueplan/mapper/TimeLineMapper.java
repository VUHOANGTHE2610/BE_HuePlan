package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.TimeLineDTO;
import com.vuhoang.hueplan.entity.TimeLineEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeLineMapper {

    @Autowired
    private UserRepository userRepository;

    public TimeLineDTO toDTO(TimeLineEntity entity) {
        TimeLineDTO dto = new TimeLineDTO();
        dto.setTimeLine_ID(entity.getTimeLine_ID());
        dto.setTimeLine_Name(entity.getTimeLine_Name());
        dto.setUser_ID(entity.getUser().getUser_ID());
        return dto;
    }

    public TimeLineEntity toEntity(TimeLineDTO dto) {
        TimeLineEntity entity = new TimeLineEntity();
        entity.setTimeLine_ID(dto.getTimeLine_ID());
        entity.setTimeLine_Name(dto.getTimeLine_Name());
        UserEntity user = userRepository.findById(dto.getUser_ID())
                .orElseThrow(() -> new IllegalArgumentException("User không tồn tại"));
        entity.setUser(user);
        return entity;
    }
}