package com.vuhoang.hueplan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteLocationDTO {
    private int favorite_ID;
    private int user_ID;
    private int location_ID;
}
