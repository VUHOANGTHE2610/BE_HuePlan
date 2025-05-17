package com.vuhoang.hueplan.controller;

import com.vuhoang.hueplan.dto.TimeLineDTO;
import com.vuhoang.hueplan.entity.ApiResponse;
import com.vuhoang.hueplan.entity.TimeLineEntity;
import com.vuhoang.hueplan.entity.UserEntity;
import com.vuhoang.hueplan.service.I_TimeLine;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/timeLine")
public class TimelineController {

    @Autowired
    I_TimeLine timeLine;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TimeLineDTO>>> getTimeLineByUserId(@PathVariable("userId") int userId) {
        List<TimeLineDTO> lst = timeLine.getTimelinesByUserId(userId);
        ApiResponse<List<TimeLineDTO>> apiResponse = new ApiResponse<>();
        if (!lst.isEmpty()) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã lây danh sách timeline người dùng với id " + userId);
            apiResponse.setData(lst);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("hành động lâý danh sách timeline của người dùng với id " + userId + "đã thất bại");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<TimeLineDTO>> addTimeline(@Valid @RequestBody TimeLineDTO timeLineDTO) {
        TimeLineDTO dto = timeLine.addTimeLine(timeLineDTO);
        return  new ResponseEntity<>(new ApiResponse<>(true, "Đã thêm timeline", dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TimeLineDTO>> updateTimeline(@PathVariable("id") int id, @Valid @RequestBody TimeLineDTO timeLineDTO) {
        timeLineDTO.setTimeLine_ID(id);
        int updated = timeLine.updateTimeLine(timeLineDTO);
        ApiResponse<TimeLineDTO> apiResponse = new ApiResponse<>();
        if (updated > 0) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã sửa lịch trình");
            apiResponse.setData(timeLineDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("không tìm thấy lịch trình");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTimeline(@PathVariable("id") int id) {
        boolean deleted = timeLine.deleteTimeLine(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        if (deleted) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("đã xóa time line");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("không tìm thaays lịch trình để xóa ");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}
