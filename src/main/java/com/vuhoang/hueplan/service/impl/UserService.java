package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.UserDTO;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.mapper.UserMapper;
import com.vuhoang.hueplan.repository.UserRepository;
import com.vuhoang.hueplan.service.I_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements I_User {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Đăng ký user mới
    public UserEntity register(UserDTO userDTO) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByUserEmail(userDTO.getUserEmail()) != null) {
            throw new RuntimeException("Email đã tồn tại");
        }
        UserEntity user = new UserEntity();
        user.setUserEmail(userDTO.getUserEmail());
        user.setUser_Password(passwordEncoder.encode(userDTO.getUserPassword()));
        user.setUser_Name(userDTO.getUserName());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    // Tìm user theo email (dùng cho đăng nhập)
    public UserEntity findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUser(int userID) {
        return userRepository.findById(userID)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với ID: " + userID));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public int updateUser(UserDTO user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userRepository.findById(userEntity.getUser_ID())
                .map(existingUser -> {
                    existingUser.setUser_Name(userEntity.getUser_Name());
                    existingUser.setUserEmail(userEntity.getUserEmail());
                    return userRepository.save(existingUser).getUser_ID(); // Lưu user đã cập nhật
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User: " + userEntity.getUser_ID()));
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
