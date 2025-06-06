package com.vuhoang.hueplan.mapper;

import com.vuhoang.hueplan.dto.CategoryDTO;
import com.vuhoang.hueplan.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDTO toCategoryDTO(CategoryEntity category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_ID(category.getCategory_ID());
        categoryDTO.setCategory_Name(category.getCategory_Name());
        categoryDTO.setCategory_Description(category.getCategory_Description());
        return categoryDTO;
    }

    public CategoryEntity toCategoryEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategory_ID(categoryDTO.getCategory_ID());
        categoryEntity.setCategory_Name(categoryDTO.getCategory_Name());
        categoryEntity.setCategory_Description(categoryDTO.getCategory_Description());
        return categoryEntity;
    }
}


