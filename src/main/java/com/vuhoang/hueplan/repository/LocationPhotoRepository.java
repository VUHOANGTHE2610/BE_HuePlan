package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.LocationPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationPhotoRepository extends JpaRepository<LocationPhotoEntity, Integer> {
}