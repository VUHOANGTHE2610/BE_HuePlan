package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.entity.BusinessEntity;
import com.vuhoang.hueplan.repository.BusinessRepository;
import com.vuhoang.hueplan.service.I_Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService implements I_Business {

    @Autowired
    private BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public BusinessEntity findByBusinessId(int businessId) {
        return businessRepository.getReferenceById(businessId);
    }

    @Override
    public List<BusinessEntity> getAllBusiness() {
        return businessRepository.findAll();
    }

    @Override
    public BusinessEntity addBusiness(BusinessEntity business) {
        return businessRepository.save(business);
    }

    @Override
    public int updateBusiness(BusinessEntity business) {
        return businessRepository.findById(business.getBusiness_ID())
                .map(
                        exisitingBusiness -> {
                            exisitingBusiness.setBusiness_Photo(business.getBusiness_Photo());
                            exisitingBusiness.setBusiness_Name(business.getBusiness_Name());
                            exisitingBusiness.setBusiness_Description(business.getBusiness_Description());
                            exisitingBusiness.setBusiness_Location(business.getBusiness_Location());
                            exisitingBusiness.setBusiness_phone(business.getBusiness_phone());
                            return businessRepository.save(exisitingBusiness).getBusiness_ID();
                        })
                .orElseThrow(() -> new RuntimeException("không tùn thấy business" + business.getBusiness_ID()));
    }

    @Override
    public boolean deleteBusiness(int businessID) {
        if (businessRepository.existsById(businessID)) {
            businessRepository.deleteById(businessID);
            return true;
        }
        return false;
    }
}
