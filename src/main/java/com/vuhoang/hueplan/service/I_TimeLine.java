package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.dto.TimeLineDTO;
import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import com.vuhoang.hueplan.entity.TimeLineEntity;

import java.sql.Time;
import java.util.List;

public interface I_TimeLine {
    List<TimeLineDTO> getListTimeLine();
    List<TimeLineDTO> getTimelinesByUserId(int userId);
    TimeLineDTO addTimeLine(TimeLineDTO timeLine);
    int updateTimeLine(TimeLineDTO timeLine);
    boolean deleteTimeLine(int timeLine_ID);
}
