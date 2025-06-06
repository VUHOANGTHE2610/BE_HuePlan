package com.vuhoang.hueplan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class DayItemDTO {
    private int item_ID;
    @JsonProperty("item_title")
    private String item_Title;
    @JsonProperty("start_time")
    private String start_Time;
    @JsonProperty("end_time")
    private String end_Time;
    private String location;
    private Double cost;
    @JsonProperty("day_ID")
    private int day_ID;
    @JsonProperty("note")
    private String note;
}
