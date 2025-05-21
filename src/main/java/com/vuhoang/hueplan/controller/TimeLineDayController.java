package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.entity.ApiResponse;
import com.vuhoang.hueplan.dto.TimeLineDayDTO;
import com.vuhoang.hueplan.service.I_TimeLineDay;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/timeLineDay")
@SecurityRequirement(name = "bearerAuth")
public class TimeLineDayController {

    @Autowired
    private I_TimeLineDay timeLineDayService;

    @GetMapping("/timeline/{timeLineId}")
    public ResponseEntity<ApiResponse<List<TimeLineDayDTO>>> getListTimeLineDayByTimeLineId(@PathVariable int timeLineId) {
        try {
            List<TimeLineDayDTO> timeLineDays = timeLineDayService.getListTimeLineDayByTimeLineId(timeLineId);
            ApiResponse<List<TimeLineDayDTO>> response = new ApiResponse<>(true, "Lấy danh sách TimeLineDay thành công", timeLineDays);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<TimeLineDayDTO>> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TimeLineDayDTO>> addTimeLineDay(@RequestBody TimeLineDayDTO timeLineDayDTO) {
        try {
            TimeLineDayDTO savedTimeLineDay = timeLineDayService.addTimeLineDay(timeLineDayDTO);
            ApiResponse<TimeLineDayDTO> response = new ApiResponse<>(true, "Thêm TimeLineDay thành công", savedTimeLineDay);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<TimeLineDayDTO> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{timeLineDay_ID}")
    public ResponseEntity<ApiResponse<Boolean>> deleteTimeLineDay(@PathVariable int timeLineDay_ID) {
        try {
            boolean isDeleted = timeLineDayService.deleteTimeLineDay(timeLineDay_ID);
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa TimeLineDay thành công", isDeleted);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Boolean> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}