package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.UserDTO;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.repository.UserRepository;
import com.vuhoang.hueplan.service.I_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements I_User {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Đăng ký user mới
    public UserEntity register(UserDTO userDTO) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByUserEmail(userDTO.getUserEmail()) != null) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Tạo user mới
        UserEntity user = new UserEntity();
        user.setUserEmail(userDTO.getUserEmail());
        user.setUser_Password(passwordEncoder.encode(userDTO.getUserPassword())); // Mã hóa mật khẩu
        user.setUser_Name(userDTO.getUserName());
        user.setRole(userDTO.getRole() != null ? userDTO.getRole() : "Client"); // Mặc định role là client

        // Lưu vào database
        return userRepository.save(user);
    }

    // Tìm user theo email (dùng cho đăng nhập)
    public UserEntity findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }
    @Override
    public UserEntity getUser(int userID) {
        return userRepository.getReferenceById(userID);
    }

    @Override
    public List<UserEntity> getAllUser() {
        System.out.println("API getAllUser được gọi!");
        return userRepository.findAll();
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public int updateUser(UserEntity user) {
        return userRepository.findById(user.getUser_ID())
                .map(existingUser -> {
                    existingUser.setUser_Name(user.getUser_Name());
                    existingUser.setUserEmail(user.getUserEmail());
                    existingUser.setUser_Password(user.getUser_Password());
                    return userRepository.save(existingUser).getUser_ID(); // Lưu user đã cập nhật
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User: " + user.getUser_ID()));
    }

    @Override
    public boolean deleteUser(int userID) {
        if (userRepository.existsById(userID)) {
            userRepository.deleteById(userID);
            return true;
        }
        return false;
    }
}
