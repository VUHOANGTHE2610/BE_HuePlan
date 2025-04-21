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

}
