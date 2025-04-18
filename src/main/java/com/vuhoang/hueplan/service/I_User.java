package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.entity.UserEntity;

import java.util.List;

public interface I_User {
    /**
     * Tìm User khi có User ID
     * @param userID ID của ngưởi dùng
     * @return đối tượng {@link UserEntity} chưa thông tin người dùng
     */
    UserEntity getUser (int userID);

    /**
     * Lấy ra tất cả người dùng đang có ở trong Database
     * @return Danh sách chưa các đối tượng kiểu {@link UserEntity}
     */
    List<UserEntity> getAllUser ();

    /**
     * Thêm người dùng
     *
     * @param user các trường dữ liệu tương ứng vói trường dữ liệu trong {@link UserEntity}
     * @return trả về số trả về đối tượng đã thêm
     */
     UserEntity addUser (UserEntity user);

    /**
     * Cập nhật thông tin người dùng
     * @param user các trường dữ liệu tương ứng vói trường dữ liệu trong {@link UserEntity}
     * @return trả về số trả về số nguyên khi thêm thành công
     */
    int updateUser (UserEntity user);

    /**
     * Xóa người dùng Dựa vào ID của người dùng
     * @param userID Xóa người Dùng dựa vào User_ID
     * @return trả về 1 nếu xóa thành công, 0 nếu không thàng công
     */
    boolean deleteUser (int userID);
}
