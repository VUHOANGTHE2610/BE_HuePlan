package com.vuhoang.hueplan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private int location_ID;
    @NotBlank
    private String location_Name;
    private String location_Description;
    private List<PhotoDTO> location_Photos;
    private Float location_Cost;
    private String location_Address;
    private boolean isStatus;
    private String createBy;
    private int user_ID;
    private int category_ID;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PhotoDTO {
        private Integer photoId;
        private String photoUrl;
    }
}