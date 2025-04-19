package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.entity.DayItemEntity;
import com.vuhoang.hueplan.entity.TimeLineDayEntity;

import java.util.List;

public interface I_DayItem {
    DayItemEntity getDayItem(int itemId);
    List<DayItemEntity> getDayItems();
    DayItemEntity AddDayItem(DayItemEntity dayItem);
    int UpdateDay(DayItemEntity day);
    void deleteDay(int ItemID) ;
}
