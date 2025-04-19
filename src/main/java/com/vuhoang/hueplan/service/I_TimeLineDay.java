package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import com.vuhoang.hueplan.entity.TimeLineEntity;

import java.util.List;

public interface I_TimeLineDay {
     TimeLineDayEntity getTimeLine( int DayID);
     List<TimeLineDayEntity> getListDay();
     TimeLineDayEntity AddDay(TimeLineDayEntity day);
     int updateDay(TimeLineDayEntity day);
     void deleteDay(int DayID);
}
