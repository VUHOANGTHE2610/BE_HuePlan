package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.DayItemDTO;
import com.vuhoang.hueplan.entity.DayItemEntity;
import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import com.vuhoang.hueplan.mapper.DayItemMapper;
import com.vuhoang.hueplan.repository.DayItemRepository;
import com.vuhoang.hueplan.repository.TimeLineDayRepository;
import com.vuhoang.hueplan.service.I_DayItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DayItemService implements I_DayItem {

    private static final Logger logger = LoggerFactory.getLogger(DayItemService.class);
    @Autowired
    private DayItemRepository dayItemRepository;

    @Autowired
    private TimeLineDayRepository timeLineDayRepository;

    @Autowired
    private DayItemMapper dayItemMapper;

    @Override
    public DayItemDTO addDayItem(DayItemDTO dayItemDTO) {
        logger.info("Adding DayItem: {}", dayItemDTO);
        TimeLineDayEntity timeLineDay = timeLineDayRepository.findById(dayItemDTO.getDay_ID())
                .orElseThrow(() -> {
                    logger.error("TimeLineDay not found with ID: {}", dayItemDTO.getDay_ID());
                    return new IllegalArgumentException("TimeLineDay not found with ID: " + dayItemDTO.getDay_ID());
                });

        DayItemEntity dayItem = dayItemMapper.toEntity(dayItemDTO);
        dayItem.setTimeLineDay(timeLineDay);

        DayItemEntity savedItem = dayItemRepository.save(dayItem);
        logger.info("Saved DayItem with ID: {}", savedItem.getItem_ID());

        DayItemDTO result = dayItemMapper.toDTO(savedItem);
        logger.info("Returning DayItemDTO: {}", result);
        return result;
    }

    @Override
    public List<DayItemDTO> getDayItemByDayID(int dayID) {
        logger.info("Fetching DayItems for dayID: {}", dayID);
        List<DayItemEntity> dayItems = dayItemRepository.findByTimeLineDayDay_ID(dayID);
        return dayItems.stream()
                .map(dayItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public int UpdateDayItem(DayItemDTO dayItemDTO) {
        logger.info("Updating DayItem: {}", dayItemDTO);
        TimeLineDayEntity timeLineDay = timeLineDayRepository.findById(dayItemDTO.getDay_ID())
                .orElseThrow(() -> {
                    logger.error("TimeLineDay not found with ID: {}", dayItemDTO.getDay_ID());
                    return new IllegalArgumentException("TimeLineDay not found with ID: " + dayItemDTO.getDay_ID());
                });

        DayItemEntity dayItem = dayItemMapper.toEntity(dayItemDTO);
        dayItem.setTimeLineDay(timeLineDay);

        DayItemEntity savedItem = dayItemRepository.save(dayItem);
        logger.info("Updated DayItem with ID: {}", savedItem.getItem_ID());
        return savedItem.getItem_ID();
    }

    @Override
    public boolean deleteDayItem(int dayItem_ID) {
        logger.info("Deleting DayItem with ID: {}", dayItem_ID);
        if (dayItemRepository.existsById(dayItem_ID)) {
            dayItemRepository.deleteById(dayItem_ID);
            logger.info("Deleted DayItem with ID: {}", dayItem_ID);
            return true;
        }
        logger.warn("DayItem not found with ID: {}", dayItem_ID);
        return false;
    }
}
