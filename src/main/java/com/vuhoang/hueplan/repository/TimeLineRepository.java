package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.TimeLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeLineRepository extends JpaRepository<TimeLineEntity, Integer> {

}
