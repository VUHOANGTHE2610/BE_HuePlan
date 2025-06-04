package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location_photo")
public class LocationPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int photo_ID;

    @Column(name = "photo_URL", nullable = false)
    private String photo_URL;

    @ManyToOne
    @JoinColumn(name = "location_ID", nullable = false)
    private LocationEntity location;
}