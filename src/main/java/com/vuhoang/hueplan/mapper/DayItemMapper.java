package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.DayItemDTO;
import com.vuhoang.hueplan.entity.DayItemEntity;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class DayItemMapper {
    private static DayItemMapper instance;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter FE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static DayItemMapper getInstance() {
        if (instance == null) {
            instance = new DayItemMapper();
        }
        return instance;
    }

    public DayItemDTO toDTO(DayItemEntity entity) {
        DayItemDTO dto = new DayItemDTO();
        dto.setItem_ID(entity.getItem_ID());
        dto.setItem_Title(entity.getItem_Title() != null ? entity.getItem_Title() : "");
        dto.setStart_Time(entity.getStart_Time() != null ? entity.getStart_Time().format(TIME_FORMATTER) : null);
        dto.setEnd_Time(entity.getEnd_Time() != null ? entity.getEnd_Time().format(TIME_FORMATTER) : null);
        dto.setLocation(entity.getLocation() != null ? entity.getLocation() : "");
        dto.setCost(entity.getCost());
        dto.setDay_ID(entity.getTimeLineDay().getDay_ID());
        dto.setNote(entity.getNote());
        return dto;
    }

    public DayItemEntity toEntity(DayItemDTO dto) {
        DayItemEntity entity = new DayItemEntity();
        entity.setItem_ID(dto.getItem_ID());
        entity.setItem_Title(dto.getItem_Title() != null ? dto.getItem_Title() : "");

        if (dto.getStart_Time() != null && !dto.getStart_Time().isEmpty()) {
            try {
                entity.setStart_Time(LocalTime.parse(dto.getStart_Time(), TIME_FORMATTER));
            } catch (Exception e) {
                try {
                    entity.setStart_Time(LocalTime.parse(dto.getStart_Time(), FE_TIME_FORMATTER));
                } catch (Exception ex) {
                    throw new IllegalArgumentException("Định dạng start_time không hợp lệ: " + dto.getStart_Time());
                }
            }
        } else {
            throw new IllegalArgumentException("start_time không được null hoặc rỗng");
        }

        if (dto.getEnd_Time() != null && !dto.getEnd_Time().isEmpty()) {
            try {
                entity.setEnd_Time(LocalTime.parse(dto.getEnd_Time(), TIME_FORMATTER));
            } catch (Exception e) {
                try {
                    entity.setEnd_Time(LocalTime.parse(dto.getEnd_Time(), FE_TIME_FORMATTER));
                } catch (Exception ex) {
                    throw new IllegalArgumentException("Định dạng end_time không hợp lệ: " + dto.getEnd_Time());
                }
            }
        } else {
            throw new IllegalArgumentException("end_time không được null hoặc rỗng");
        }

        entity.setLocation(dto.getLocation() != null ? dto.getLocation() : "");
        entity.setCost(dto.getCost());
        entity.setNote(dto.getNote());
        return entity;
    }
}