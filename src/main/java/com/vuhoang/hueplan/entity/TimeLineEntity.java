package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "timelines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeLine_ID;

    @Column(name = "timeLine_Name")
    private String timeLine_Name;

    @ManyToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "timeLine", cascade = CascadeType.ALL, orphanRemoval = true)
    private TimeLineDayEntity timeLineDay;
}
