package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "day_items")
public class DayItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_ID;

    @Column(name = "item_Title")
    private String item_Title;

    @Column(name = "start_Time")
    private LocalTime start_Time;

    @Column(name = "end_Time")
    private LocalTime end_Time;

    @Column(name = "location")
    private String location;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "day_ID", nullable = false)
    private TimeLineDayEntity timeLineDay;

}
