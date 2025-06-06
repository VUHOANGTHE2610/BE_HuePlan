package com.vuhoang.hueplan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuhoang.hueplan.dto.LocationDTO;
import com.vuhoang.hueplan.entity.ApiResponse;
import com.vuhoang.hueplan.entity.LocationEntity;
import com.vuhoang.hueplan.entity.LocationPhotoEntity;
import com.vuhoang.hueplan.service.I_Location;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/location")
@SecurityRequirement(name = "bearerAuth")
public class LocationController {
    @Autowired
    I_Location location;

    @GetMapping("/getAllTrue")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> getAllStatusIsTrue() {
        List<LocationDTO> lstDto = location.getLocationsByIsStatus();
        ApiResponse<List<LocationDTO>> apiResponse = new ApiResponse<>();
        if (!lstDto.isEmpty()) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã lấy tat cả địa điểm có isStatuss là true");
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi khi lấy tất cả địa điểm có có isStatuss là true ");
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllFalse")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> getAllStatusIsTFalse() {
        List<LocationDTO> lstDto = location.getLocationsByIsStatusIsFalse();
        ApiResponse<List<LocationDTO>> apiResponse = new ApiResponse<>();
        if (!lstDto.isEmpty()) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã lấy tat cả địa điểm có isStatuss là true");
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi khi lấy tất cả địa điểm có có isStatuss là true ");
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
            @GetMapping("/getAllByID/{CategoryID}")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> getAllByID(@PathVariable int CategoryID) {
        List<LocationDTO> lstDto = location.getLocationsByCategory(CategoryID);
        ApiResponse<List<LocationDTO>> apiResponse = new ApiResponse<>();
        if (!lstDto.isEmpty()) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("đã lay tất cả danh sách với" + CategoryID);
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("lỗi khi lấy danh sách với " + CategoryID);
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getByID/{Userid}")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> getByID(@PathVariable int Userid) {
        List<LocationDTO> lstDto = location.getLocationsByUser(Userid);
        ApiResponse<List<LocationDTO>> apiResponse = new ApiResponse<>();
        if (!lstDto.isEmpty()) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("đã lấy danh sách địa điểm với user id thành công");
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("đã có lỗi khi lấy danh người dùng với" + Userid);
            apiResponse.setData(lstDto);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<LocationDTO>>> getLocations() {
        List<LocationDTO> lst = location.getLocations();
        ApiResponse<List<LocationDTO>> apiResponse = new ApiResponse<>();
        if (!lst.isEmpty()) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã lấy ve danh sach tất cả Các địa điểm");
            apiResponse.setData(lst);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Có lỗi khi lấy tất cả danh sách địa điểm");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> getLocationById(@PathVariable("id") int id) {
        LocationDTO locationDTO = location.getLocationById(id);
        ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
        if (locationDTO != null) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Lấy thông tin địa điểm thành công");
            apiResponse.setData(locationDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Không tìm thấy địa điểm");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<LocationDTO>> addLocation(
            @RequestPart(name = "location", required = true) String locationJson,
            @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        try {
            // Parse chuỗi JSON thành LocationDTO
            ObjectMapper objectMapper = new ObjectMapper();
            LocationDTO locationDTO = objectMapper.readValue(locationJson, LocationDTO.class);

            // Validate LocationDTO
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<LocationDTO>> violations = validator.validate(locationDTO);
            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(v -> v.getMessage())
                        .collect(Collectors.joining(", "));
                throw new IllegalArgumentException("Dữ liệu không hợp lệ: " + errorMessage);
            }

            System.out.println("Received location: " + locationDTO);
            System.out.println("Received files: " + (files != null ? files.size() : 0));
            LocationDTO dto = location.addLocation(locationDTO, files);
            ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
            if (dto != null) {
                apiResponse.setSuccess(true);
                apiResponse.setMessage("Đã thêm địa điểm");
                apiResponse.setData(dto);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse.setSuccess(false);
                apiResponse.setMessage("Đã có lỗi khi thêm địa điểm");
                apiResponse.setData(null);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi khi xử lý ảnh: " + e.getMessage());
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi: " + e.getMessage());
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> updateLocation(
            @PathVariable("id") int id,
            @Valid @RequestBody LocationDTO locationDTO) {
        locationDTO.setUser_ID(locationDTO.getUser_ID());
        int updated = location.updateLocation(locationDTO);
        ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
        if (updated > 0) {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã chỉnh sửa địa điểm");
            apiResponse.setData(locationDTO);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Chưa update địa điểm vị đang có vấn đề ");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/status/{isStatus}")
    @Transactional
    public ResponseEntity<ApiResponse<LocationDTO>> setIsStatus(
            @PathVariable("id") int id,
            @PathVariable("isStatus") boolean isStatus) {

        try {
            int updatedLocation = location.setIsStatus(id, isStatus);
            ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();

            if (updatedLocation == 1) {
                apiResponse.setSuccess(true);
                apiResponse.setMessage("Đã cập nhật trạng thái cho bài viết thành công");
                apiResponse.setData(null);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse.setSuccess(false);
                apiResponse.setMessage("Không tìm thấy bài viết để cập nhật trạng thái");
                apiResponse.setData(null);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Đã xảy ra lỗi khi cập nhật trạng thái: " + e.getMessage());
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<LocationDTO>> deleteLocation(@PathVariable("id") int id) {
        ApiResponse<LocationDTO> apiResponse = new ApiResponse<>();
        if(location.deleteLocation(id)) {
           apiResponse.setSuccess(true);
           apiResponse.setMessage("Đã xóa địa điểm thành công");
           apiResponse.setData(null);
           return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Chưa xóa được vì có lỗi ở đâu đó, hãy kiểm tra lại đã truyền id chưa");
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/photos")
    public ResponseEntity<ApiResponse<LocationPhotoEntity>> uploadPhoto(
            @PathVariable("id") int id,
            @RequestParam("file") MultipartFile file) {
        try {
            LocationPhotoEntity photo = location.uploadPhoto(id, file);
            ApiResponse<LocationPhotoEntity> apiResponse = new ApiResponse<>();
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Đã tải ảnh lên thành công");
            apiResponse.setData(photo);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (IOException e) {
            ApiResponse<LocationPhotoEntity> apiResponse = new ApiResponse<>();
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi khi tải ảnh: " + e.getMessage());
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/photos/{photoId}")
    public ResponseEntity<ApiResponse<Void>> deletePhoto(@PathVariable("photoId") int photoId) {
        try {
            boolean deleted = location.deletePhoto(photoId);
            ApiResponse<Void> apiResponse = new ApiResponse<>();
            if (deleted) {
                apiResponse.setSuccess(true);
                apiResponse.setMessage("Đã xóa ảnh thành công");
                apiResponse.setData(null);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            } else {
                apiResponse.setSuccess(false);
                apiResponse.setMessage("Không tìm thấy ảnh để xóa");
                apiResponse.setData(null);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            ApiResponse<Void> apiResponse = new ApiResponse<>();
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Lỗi khi xóa ảnh: " + e.getMessage());
            apiResponse.setData(null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
