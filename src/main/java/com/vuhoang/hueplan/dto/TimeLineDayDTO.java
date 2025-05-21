package com.vuhoang.hueplan.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TimeLineDayDTO {
    private int day_ID;
    private LocalDate day_Date;
    private int timeLine_ID;
    private List<DayItemDTO> dayItemList;
}
