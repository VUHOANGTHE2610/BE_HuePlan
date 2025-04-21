package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.dto.LoginResponse;
import com.vuhoang.hueplan.dto.RegisterResponse;
import com.vuhoang.hueplan.dto.UserDTO;
import com.vuhoang.hueplan.entity.BusinessEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.service.I_Business;
import com.vuhoang.hueplan.service.impl.UserService;
import com.vuhoang.hueplan.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private I_Business iBusiness;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            // Xác thực email và mật khẩu
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getUserEmail(),
                            userDTO.getUserPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserEntity user = (UserEntity) authentication.getPrincipal();

            // Tạo JWT token
            String jwt = jwtUtil.generateToken(user);

            // Trả về response
            return ResponseEntity.ok(new LoginResponse(
                    user.getUser_ID(),
                    user.getUser_Name(),
                    user.getUserEmail(),
                    user.getRole(),
                    jwt
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đăng nhập thất bại: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserEntity user = userService.register(userDTO);
            return ResponseEntity.ok(new RegisterResponse("Đăng ký thành công", user.getUser_ID()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Đăng ký thất bại: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}