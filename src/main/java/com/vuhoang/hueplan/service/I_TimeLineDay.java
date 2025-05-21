package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.dto.TimeLineDayDTO;

import java.util.List;

public interface I_TimeLineDay {

    List<TimeLineDayDTO> getListTimeLineDayByTimeLineId(int timeLineId);
    TimeLineDayDTO addTimeLineDay(TimeLineDayDTO timeLineDay);
    boolean deleteTimeLineDay(int timeLineDay_ID);
}
