package com.vuhoang.hueplan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private int user_ID;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String userEmail;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String userPassword;

    @NotBlank(message = "Tên không được để trống")
    private String userName;

    @NotBlank(message = "Vai trò không được để trống")
    private String role;

//    @Column(name = "isStatus")
//    private boolean isStatus;

    private List<FavoriteLocationDTO> favoriteLocations;
}