package com.vuhoang.hueplan.repository;

import com.vuhoang.hueplan.entity.BusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity, Integer> {
}
