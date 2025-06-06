package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.dto.LocationDTO;
import com.vuhoang.hueplan.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LocationEntity l WHERE l.location_Name = :locationName")
    boolean existsByLocation_Name(@Param("locationName") String locationName);

    @Query("SELECT l FROM LocationEntity l WHERE l.user.user_ID = :userID")
    List<LocationEntity> getLocationsByUser( int userID);

    @Query("SELECT l FROM LocationEntity l WHERE l.isStatus = true")
    List<LocationEntity> getLocationsByIsStatus();

    @Query("SELECT l FROM LocationEntity l WHERE l.isStatus = false ")
    List<LocationEntity> getLocationsByIsStatusIsFalse();

    @Query("SELECT l FROM LocationEntity l WHERE l.category.category_ID = :categoryID AND l.isStatus = true")
    List<LocationEntity> getLocationsByCategoryId(@Param("categoryID") int categoryID);

}
