package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.dto.DayItemDTO;
import java.util.List;

public interface I_DayItem {
    DayItemDTO addDayItem(DayItemDTO dayItemDTO);
    List<DayItemDTO> getDayItemByDayID(int dayID);
    int UpdateDayItem(DayItemDTO dayItemDTO);
    boolean deleteDayItem(int dayItem_ID);
}