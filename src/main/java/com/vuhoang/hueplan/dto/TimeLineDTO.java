package com.vuhoang.hueplan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class TimeLineDTO {
    private int timeLine_ID;

    @NotBlank(message = "tên của time line không được để trống !")
    private String timeLine_Name;

    private int user_ID;
    private List<TimeLineDayDTO> timeLineDay;
}
