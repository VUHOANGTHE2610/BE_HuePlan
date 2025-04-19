package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import com.vuhoang.hueplan.entity.TimeLineEntity;

import java.util.List;

public interface I_TimeLine {
    TimeLineEntity getTimeLine();
    List<TimeLineDayEntity> getLisiTimeLine();
    int updateTimeLine(TimeLineEntity timeLineEntity);
    TimeLineEntity addTimeLine(TimeLineEntity timeLineEntity);
    void deleteTimeLine(int TimeLineID);
}
