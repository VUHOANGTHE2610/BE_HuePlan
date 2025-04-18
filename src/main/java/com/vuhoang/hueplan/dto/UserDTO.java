package com.vuhoang.hueplan.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String role;
}