package com.vuhoang.hueplan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLineDayDTO {
    private int day_ID;
    private LocalDate day_Date;
    private int timeLine_ID;

    private List<DayItemDTO> list_day_item;
}
