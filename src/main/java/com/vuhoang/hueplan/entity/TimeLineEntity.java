package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "timelines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeLine_ID")
    private int timeLine_ID;

    @Column(name = "timeLine_Name")
    private String timeLine_Name;

    @OneToMany(mappedBy = "timeLine", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonManagedReference
    private List<TimeLineDayEntity> timeLineDay;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    @com.fasterxml.jackson.annotation.JsonBackReference
    private UserEntity user;
}
