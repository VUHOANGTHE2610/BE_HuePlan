package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.TimeLineDTO;
import com.vuhoang.hueplan.entity.TimeLineEntity;
import com.vuhoang.hueplan.mapper.TimeLineMapper;
import com.vuhoang.hueplan.repository.TimeLineRepository;
import com.vuhoang.hueplan.service.I_TimeLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeLineService implements I_TimeLine {

    @Autowired
    TimeLineRepository timeLineRepository;

    @Autowired
    TimeLineMapper timeLineMapper;


    @Override
    public List<TimeLineDTO> getListTimeLine() {
        return timeLineRepository.findAll().stream()
                .map(timeLineMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeLineDTO> getTimelinesByUserId(int userId) {
        return  timeLineRepository.findByUserId(userId).stream()
                .map(timeLineMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TimeLineDTO addTimeLine(TimeLineDTO timeLine) {
        TimeLineEntity timeLineEntity = timeLineMapper.toEntity(timeLine);
        timeLineRepository.save(timeLineEntity);
        return timeLineMapper.toDTO(timeLineEntity);
    }

    @Override
    public int updateTimeLine(TimeLineDTO timeLine) {
        if(timeLineRepository.existsById(timeLine.getTimeLine_ID())) {
            TimeLineEntity timeLineEntity = timeLineMapper.toEntity(timeLine);
            timeLineRepository.save(timeLineEntity);
            return 1;
        }
        return 0;
    }

    @Override
    public boolean deleteTimeLine(int timeLine_ID) {
        if(timeLineRepository.existsById(timeLine_ID)) {
            timeLineRepository.deleteById(timeLine_ID);
            return true;
        }
        return false;
    }
}
