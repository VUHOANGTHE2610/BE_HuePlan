package com.vuhoang.hueplan.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class BusinessDTO {
    private Integer business_ID;

    private String business_Photo;

    @NotBlank(message = "Tên không được để trống")
    private String business_Name;

    @NotBlank(message = "Địa điểm không được để trống")
    private String business_Location;

    @NotBlank(message = "Chi phí không được để trống")
    private Double business_Cost;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String business_phone;

    @NotBlank(message = "Mô tả không được để trống")
    private String business_Description;

    @NotNull(message = "User ID không được để trống")
    private Integer user_ID;
}
