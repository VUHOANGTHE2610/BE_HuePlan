package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.TimeLineDayDTO;
import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TimeLineDayMapper {
    private static TimeLineDayMapper instance;

    public static TimeLineDayMapper getInstance() {
        if (instance == null) {
            instance = new TimeLineDayMapper();
        }
        return instance;
    }

    public TimeLineDayDTO toDTO(TimeLineDayEntity entity) {
        TimeLineDayDTO dto = new TimeLineDayDTO();
        dto.setDay_ID(entity.getDay_ID());
        dto.setDay_Date(entity.getDay_Date());
        dto.setTimeLine_ID(entity.getTimeLine().getTimeLine_ID());
        if (entity.getDayItem() != null) {
            dto.setDayItemList(entity.getDayItem().stream()
                    .map(DayItemMapper.getInstance()::toDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public TimeLineDayEntity toEntity(TimeLineDayDTO dto) {
        TimeLineDayEntity entity = new TimeLineDayEntity();
        entity.setDay_ID(dto.getDay_ID());
        entity.setDay_Date(dto.getDay_Date());
        // timeLine cần được gán thủ công qua service dựa trên timeLine_ID
        return entity;
    }
}
