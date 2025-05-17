package com.vuhoang.hueplan.dto;

import lombok.Data;


@Data
public class DayItemDTO {
    private int item_ID;
    private String item_Title;
    private String start_Time;
    private String end_Time;
    private String location;
    private Double cost;
    private int day_ID;
}
