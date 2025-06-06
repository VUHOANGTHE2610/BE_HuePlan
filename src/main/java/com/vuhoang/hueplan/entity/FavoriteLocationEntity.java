package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favorite_ID;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "location_ID", nullable = false)
    private LocationEntity location;
}
