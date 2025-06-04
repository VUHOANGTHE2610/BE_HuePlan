package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.DayItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayItemRepository extends JpaRepository<DayItemEntity, Integer> {
    @Query("SELECT d FROM DayItemEntity d WHERE d.timeLineDay.day_ID = :dayID")
    List<DayItemEntity> findByTimeLineDayDay_ID(@Param("dayID") int dayID);
}

