package com.vuhoang.hueplan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String userEmail;

    @NotBlank(message = "Mật khẩu không được để trống")
    private String userPassword;

    @NotBlank(message = "Tên không được để trống")
    private String userName;

    @NotBlank(message = "Vai trò không được để trống")
    private String role;

    @JsonProperty("business")
    private BusinessDTO businessDTO;
}