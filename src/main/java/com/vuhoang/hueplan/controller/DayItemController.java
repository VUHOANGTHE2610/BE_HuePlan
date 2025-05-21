package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.entity.ApiResponse;
import com.vuhoang.hueplan.dto.DayItemDTO;
import com.vuhoang.hueplan.service.I_DayItem;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/dayItem")
@SecurityRequirement(name = "bearerAuth")
public class DayItemController {

    @Autowired
    private I_DayItem dayItemService;

    @GetMapping("/day/{dayID}")
    public ResponseEntity<ApiResponse<List<DayItemDTO>>> getDayItemByDayID(@PathVariable int dayID) {
        try {
            List<DayItemDTO> dayItems = dayItemService.getDayItemByDayID(dayID);
            ApiResponse<List<DayItemDTO>> response = new ApiResponse<>(true, "Lấy danh sách DayItem thành công", dayItems);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<DayItemDTO>> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DayItemDTO>> addDayItem(@RequestBody DayItemDTO dayItemDTO) {
        try {
            System.out.println("Dữ liệu nhận từ frontend (addDayItem): " + dayItemDTO); // Thêm log
            DayItemDTO savedDayItem = dayItemService.addDayItem(dayItemDTO);
            ApiResponse<DayItemDTO> response = new ApiResponse<>(true, "Thêm DayItem thành công", savedDayItem);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<DayItemDTO> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{item_ID}")
    public ResponseEntity<ApiResponse<Integer>> updateDayItem(@PathVariable int item_ID, @RequestBody DayItemDTO dayItemDTO) {
        try {
            dayItemDTO.setItem_ID(item_ID);
            int updatedId = dayItemService.UpdateDayItem(dayItemDTO);
            ApiResponse<Integer> response = new ApiResponse<>(true, "Cập nhật DayItem thành công", updatedId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Integer> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{dayItem_ID}")
    public ResponseEntity<ApiResponse<Boolean>> deleteDayItem(@PathVariable int dayItem_ID) {
        try {
            boolean isDeleted = dayItemService.deleteDayItem(dayItem_ID);
            ApiResponse<Boolean> response = new ApiResponse<>(true, "Xóa DayItem thành công", isDeleted);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Boolean> response = new ApiResponse<>(false, e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}