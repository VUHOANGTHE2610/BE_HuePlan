package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "business")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int business_ID;

    @Column(name = "business_photo")
    private String business_Photo;

    @Column(name = "business_Name")
    private String business_Name;

    @Column(name = "business_Location")
    private String business_Location;

    @Column(name = "cost")
    private String business_Cost;

    @Column(name = "business_phone")
    private String business_phone;

    @Column(name = "business_Description")
    private String business_Description;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private UserEntity user;
}
