package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int location_ID;

    @Column(name = "location_Name")
    private String location_Name;

    @Column(name = "location_Description")
    private String location_Description;

    @Column(name = "location_Photos")
    private String location_Photos;

    @Column(name = "location_Cost")
    private Float location_Cost;

    @Column(name = "Location_Address")
    private String Location_Address;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private UserEntity user;
}
