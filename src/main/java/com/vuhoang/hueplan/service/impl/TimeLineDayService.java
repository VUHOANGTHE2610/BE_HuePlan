package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.TimeLineDayDTO;
import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import com.vuhoang.hueplan.entity.TimeLineEntity;
import com.vuhoang.hueplan.mapper.TimeLineDayMapper;
import com.vuhoang.hueplan.repository.TimeLineDayRepository;
import com.vuhoang.hueplan.repository.TimeLineRepository;
import com.vuhoang.hueplan.service.I_TimeLineDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimeLineDayService implements I_TimeLineDay {
    @Autowired
    private TimeLineDayRepository timeLineDayRepository;

    @Autowired
    private TimeLineRepository timeLineRepository;

    @Autowired
    private TimeLineDayMapper timeLineDayMapper;

    @Override
    public List<TimeLineDayDTO> getListTimeLineDayByTimeLineId(int timeLineId) {
        return timeLineDayRepository.findByTimeLine_TimeLine_ID(timeLineId)
            .stream()
            .map(timeLineDayMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public TimeLineDayDTO addTimeLineDay(TimeLineDayDTO timeLineDay) {
        TimeLineDayEntity entity = timeLineDayMapper.toEntity(timeLineDay);
        TimeLineEntity timeLine = timeLineRepository.findById(timeLineDay.getTimeLine_ID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy timeline với ID: " + timeLineDay.getTimeLine_ID()));
        entity.setTimeLine(timeLine);
        TimeLineDayEntity savedEntity = timeLineDayRepository.save(entity);
        return timeLineDayMapper.toDTO(savedEntity);
    }

    @Override
    public boolean deleteTimeLineDay(int timeLineDay_ID) {
        if (timeLineDayRepository.existsById(timeLineDay_ID)) {
            timeLineDayRepository.deleteById(timeLineDay_ID);
            return true;
        }
        return false;
    }
}
