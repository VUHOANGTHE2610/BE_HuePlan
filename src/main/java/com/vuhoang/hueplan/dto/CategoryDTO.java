package com.vuhoang.hueplan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private int category_ID;
    private String category_Name;
    private String category_Description;
}
