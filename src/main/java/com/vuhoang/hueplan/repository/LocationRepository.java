package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LocationEntity l WHERE l.location_Name = :locationName")
    boolean existsByLocation_Name(@Param("locationName") String locationName);
}
