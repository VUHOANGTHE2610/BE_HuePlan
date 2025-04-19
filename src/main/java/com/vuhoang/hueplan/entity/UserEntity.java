package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_ID;

    @Column(name = "user_Name")
    private String user_Name;

    @Column(name = "user_Password")
    private String user_Password;

    @Column(name = "user_Email")
    private String userEmail;

    @Column(name = "role")  // (client - Admin - cooperator - business)
    private String role;


    // các mối quan hệ
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private BusinessEntity business;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeLineEntity> timeLine;


    @Override
    @Transient  // Không ánh xạ phương thức này vào database
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    @Transient  // Không ánh xạ phương thức này vào database
    public String getPassword() {
        return user_Password;
    }

    @Override
    @Transient  // Không ánh xạ phương thức này vào database
    public String getUsername() {
        return userEmail;  // Sử dụng email làm username
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
