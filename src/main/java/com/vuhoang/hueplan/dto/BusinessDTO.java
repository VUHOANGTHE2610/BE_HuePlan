package com.vuhoang.hueplan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class BusinessDTO {
    @JsonProperty("business_name")
    private String business_Name;

    @JsonProperty("business_photo")
    private String business_Photo;

    @NotBlank(message = "Địa điểm không được để trống")
    @JsonProperty("business_location")
    private String business_Location;

    @JsonProperty("cost")
    private Double business_Cost;

    @NotBlank(message = "Số điện thoại không được để trống")
    @JsonProperty("business_phone")
    private String business_phone;

    @JsonProperty("business_description")
    private String business_Description;
}
