package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.dto.UserDTO;
import com.vuhoang.hueplan.entity.ApiResponse;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.service.impl.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@SecurityRequirement(name = "bearerAuth")
public class   UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUser() {
        List<UserDTO> users = userService.getAllUser();
        ApiResponse<List<UserDTO>> response = new ApiResponse<>();
        if (users != null & !users.isEmpty()) {
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Đã lấy toàn bộ danh sách Ngươi dùng = getAllUser() /n");
            response.setData(users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("Có lỗi xảy ra, có khả năng chưa có dữ liệu đầu vào kiểm tra lại lỗi này báo từ UserController hàm getAllUser() ");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable("id") int id) {
        UserDTO user = userService.getUser(id);
        ApiResponse<UserDTO> response = new ApiResponse<>();
        if (user != null){
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Đã lấy đối tượng người dùng với " + id + " getUserById() /n");
            response.setData(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("không thế tìm thấy người dùng với" + id + "này");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

        @PutMapping("/update")
    public ResponseEntity<ApiResponse<Integer>> updateUser(@RequestBody UserDTO user) {
        Integer tmp =  userService.updateUser(user);
        ApiResponse<Integer> response = new ApiResponse<>();
        if (tmp != null){
            response.setSuccess(Boolean.TRUE);
            response.setMessage("đói tượng đã được cập nhật");
            response.setData(tmp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("LỖI: thông tin chưa được cập nhật");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteUser(@PathVariable("id") int id) {
        Boolean tmp = userService.deleteUser(id);
        ApiResponse<Boolean> response = new ApiResponse<>();
        if (tmp != null){
            response.setSuccess(Boolean.TRUE);
            response.setMessage("Đã xóa user với id là " + id);
            response.setData(tmp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("Lỗi, không thể xóa với id là" + id);
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
