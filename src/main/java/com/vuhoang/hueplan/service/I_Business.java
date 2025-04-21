package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.entity.BusinessEntity;

import java.util.List;

public interface I_Business {
    BusinessEntity findByBusinessId(int businessId);
    int updateBusiness (BusinessEntity business);
    void deleteBusiness (int businessID);
}
