package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.CategoryDTO;
import com.vuhoang.hueplan.dto.LocationDTO;
import com.vuhoang.hueplan.entity.CategoryEntity;
import com.vuhoang.hueplan.mapper.CategoryMapper;
import com.vuhoang.hueplan.repository.CategoryRepository;
import com.vuhoang.hueplan.service.I_Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService implements I_Category {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getAllCategory() {
        var lst = categoryRepository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();
        for (CategoryEntity entity : lst) {
            CategoryDTO dto = categoryMapper.toCategoryDTO(entity);
            dtos.add(dto);
        }
        return dtos;
    }
}
