package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.dto.CategoryDTO;
import com.vuhoang.hueplan.entity.ApiResponse;
import com.vuhoang.hueplan.service.I_Category;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    @Autowired
    I_Category category;

    @GetMapping("/GetAll")
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getAll() {
        var lst = category.getAllCategory();
        ApiResponse<List<CategoryDTO>> apiResponse = new ApiResponse<>();
        if (lst != null) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("lấy danh sách loại địa điểm thành công");
            apiResponse.setData(lst);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi khi lấy danh sách loại địa điểm");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

    }
}
