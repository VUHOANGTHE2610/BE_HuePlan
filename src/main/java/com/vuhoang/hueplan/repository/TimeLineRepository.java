package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.TimeLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLineEntity, Integer> {
    @Query("SELECT t FROM TimeLineEntity t WHERE t.user.user_ID = :userId")
    List<TimeLineEntity> findByUserId(@Param("userId") int userId);
}
