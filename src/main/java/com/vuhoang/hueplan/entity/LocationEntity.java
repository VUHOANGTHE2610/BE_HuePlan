package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int location_ID;

    @Column(name = "location_Name")
    private String location_Name;

    @Column(name = "location_Description")
    private String location_Description;

    @Column(name = "location_Cost")
    private Float location_Cost;

    @Column(name = "location_Address")
    private String location_Address;

    @Column(name = "isStatus")
    private boolean isStatus;

    @Column(name = "createBy")
    private String createBy;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private UserEntity user;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationPhotoEntity> photos;
}