package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.TimeLineDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLineDayRepository extends JpaRepository<TimeLineDayEntity, Integer> {
    @Query("SELECT t FROM TimeLineDayEntity t WHERE t.timeLine.timeLine_ID = :timeLineId")
    List<TimeLineDayEntity> findByTimeLine_TimeLine_ID(@Param("timeLineId") int timeLineId);
}
