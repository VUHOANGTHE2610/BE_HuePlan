package com.vuhoang.hueplan.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private String message;
    private int userId;

    public RegisterResponse(String message, int userId) {
        this.message = message;
        this.userId = userId;
    }
}