package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.dto.BusinessDTO;
import com.vuhoang.hueplan.entity.BusinessEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.mapper.BusinessMapper;
import com.vuhoang.hueplan.repository.BusinessRepository;
import com.vuhoang.hueplan.service.I_Business;
import com.vuhoang.hueplan.service.I_User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/business")
public class BusinessController {
    @Autowired
    private I_Business businessService;

    @Autowired
    private I_User userService;

    @Autowired
    private BusinessMapper businessMapper;

    @GetMapping("/{id}")
    public BusinessDTO getBusinessById(@PathVariable int businessID){
        BusinessEntity business = businessService.findByBusinessId(businessID);
        return businessMapper.toDTO(business);
    }

    @PostMapping("/add")
    public BusinessDTO createBusiness(@Valid @RequestBody BusinessDTO businessDTO){
        UserEntity user = userService.getUser(businessDTO.getUser_ID());
        BusinessEntity businessEntity = businessMapper.toEntity(businessDTO,user);
        BusinessEntity saved = businessService.addBusiness(businessEntity);
        return businessMapper.toDTO(saved);
    }

    @PutMapping("/update")
    public int updateBusiness(@Valid @RequestBody BusinessDTO businessDTO){
        UserEntity user = userService.getUser(businessDTO.getUser_ID());
        BusinessEntity businessEntity = businessMapper.toEntity(businessDTO,user);
        return businessService.updateBusiness(businessEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBusiness(@PathVariable int id){
        BusinessEntity businessEntity = businessService.findByBusinessId(id);
        if(businessEntity != null){
            businessService.deleteBusiness(id);
        }

    }

}
