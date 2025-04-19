package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.entity.BusinessEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.repository.BusinessRepository;
import com.vuhoang.hueplan.service.I_Business;
import com.vuhoang.hueplan.service.I_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService implements I_Business {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private I_User userService;


    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Override
    public BusinessEntity findByBusinessId(int businessId) {
        return businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy business với ID: " + businessId));
    }


    @Override
    public BusinessEntity addBusiness(BusinessEntity business) {
        UserEntity user = business.getUser();
        if (user.getBusiness() != null) {
            throw new RuntimeException("User đã có một business, không thể tạo thêm!");
        }
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
                            exisitingBusiness.setBusiness_Cost(business.getBusiness_Cost());
                            return businessRepository.save(exisitingBusiness).getBusiness_ID();
                        })
                .orElseThrow(() -> new RuntimeException("không tùn thấy business" + business.getBusiness_ID()));
    }

    @Override
    public void deleteBusiness(int businessID) {
        if (!businessRepository.existsById(businessID)) {
            throw new RuntimeException("Không tìm thấy business với ID: " + businessID);
        }
        businessRepository.deleteById(businessID);
    }
}
