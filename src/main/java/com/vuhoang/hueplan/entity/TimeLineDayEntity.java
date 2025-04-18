package com.vuhoang.hueplan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "timeline_days")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeLineDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int day_ID;

    @Column(name = "day_Date")
    private LocalDate day_Date;

    @ManyToOne
    @JoinColumn(name = "timeLine_ID", nullable = false)
    private TimeLineEntity timeLine;

    @OneToMany(mappedBy = "timeLineDay" , cascade = CascadeType.ALL, orphanRemoval = true)
    private DayItemEntity dayItem;

}
