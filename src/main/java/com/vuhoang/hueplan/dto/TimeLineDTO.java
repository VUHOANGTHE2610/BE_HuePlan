package com.vuhoang.hueplan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLineDTO {
    private int timeLine_ID;

    @NotBlank(message = "Tên không được để trống")
    private String timeLine_Name;

    private int user_ID;
    private List<TimeLineDayDTO> list_time_day;
}
