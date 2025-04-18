package com.vuhoang.hueplan.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private int userId;
    private String userName;
    private String userEmail;
    private String role;
    private String token;

    public LoginResponse(int userId, String userName, String userEmail, String role, String token) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
        this.token = token;
    }
}