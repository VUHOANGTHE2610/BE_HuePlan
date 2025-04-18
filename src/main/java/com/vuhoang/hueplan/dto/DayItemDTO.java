package com.vuhoang.hueplan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayItemDTO {
    private int item_ID;
    private String item_Title;
    private LocalTime start_Time;
    private LocalTime end_Time;
    private String location;
    private Double cost;
}
