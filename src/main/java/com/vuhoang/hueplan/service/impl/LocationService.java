package com.vuhoang.hueplan.service.impl;

import com.vuhoang.hueplan.dto.LocationDTO;
import com.vuhoang.hueplan.entity.LocationEntity;
import com.vuhoang.hueplan.entity.LocationPhotoEntity;
import com.vuhoang.hueplan.mapper.LocationMapper;
import com.vuhoang.hueplan.repository.LocationPhotoRepository;
import com.vuhoang.hueplan.repository.LocationRepository;
import com.vuhoang.hueplan.service.I_Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LocationService implements I_Location {
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationPhotoRepository locationPhotoRepository;

    @Autowired
    private LocationMapper locationMapper;

    //TODO: theem phần chỉ lấy những địa điểm có isStatus = 1
    @Override
    public List<LocationDTO> getLocations() {
        List<LocationEntity> lst = locationRepository.findAll();
        List<LocationDTO> lstDto = new ArrayList<>();
        for (LocationEntity entity : lst) {
            LocationDTO dto = locationMapper.toDTO(entity);
            lstDto.add(dto);
        }
        return lstDto;
    }

    @Override
    public LocationDTO getLocationById(int id) {
        return locationRepository.findById(id)
                .map(locationMapper::toDTO)
                .orElse(null);
    }

    @Override
    public boolean findByLocation_Name(String location_Name) {
      return locationRepository.existsByLocation_Name(location_Name);
    }

    @Override
    public LocationDTO addLocation(LocationDTO locationDTO, List<MultipartFile> files) throws IOException {
        if (locationDTO == null) {
            throw new IllegalArgumentException("Dữ liệu địa điểm không được để trống");
        }
        LocationEntity entity = locationMapper.toEntity(locationDTO);
        if (findByLocation_Name(entity.getLocation_Name())) {
            throw new IllegalArgumentException("Tên địa điểm đã tồn tại trong hệ thống");
        }

        // Lưu địa điểm
        entity = locationRepository.save(entity);

        // Xử lý upload ảnh nếu có
        if (files != null && !files.isEmpty()) {
            String uploadDir = "src/main/resources/static/location/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            List<LocationPhotoEntity> photos = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                if (fileName == null || fileName.isEmpty()) {
                    throw new IllegalArgumentException("File không hợp lệ");
                }

                // Kiểm tra phần mở rộng file
                String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (!extension.matches("jpg|jpeg|png|gif")) {
                    throw new IllegalArgumentException("File " + fileName + " không phải ảnh");
                }
                if (file.getSize() > 5 * 1024 * 1024) {
                    throw new IllegalArgumentException("File " + fileName + " vượt quá 5MB");
                }

                String newFileName = UUID.randomUUID() + "_" + fileName;
                Path filePath = Paths.get(uploadDir, newFileName);
                Files.write(filePath, file.getBytes());

                LocationPhotoEntity photo = new LocationPhotoEntity();
                photo.setPhoto_URL("/location/" + newFileName);
                photo.setLocation(entity);
                photos.add(locationPhotoRepository.save(photo));
            }
            entity.setPhotos(photos);
        }

        return locationMapper.toDTO(entity);
    }
    @Override
    public int updateLocation(LocationDTO locationDTO) {
        if(locationRepository.existsById(locationDTO.getLocation_ID())) {
            LocationEntity entity = locationMapper.toEntity(locationDTO);
            locationRepository.save(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public int setIsStatus(int location_ID, boolean isStatus) {
        if(locationRepository.existsById(location_ID)) {
            LocationEntity entity = locationRepository.findById(location_ID).get();
            entity.setStatus(isStatus);
            locationRepository.save(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public boolean deleteLocation(int location_ID) {
        if(locationRepository.existsById(location_ID)) {
            locationRepository.deleteById(location_ID);
            return true;
        }
        return false;
    }

    @Override
    public LocationPhotoEntity uploadPhoto(int locationId, MultipartFile file) throws IOException {
        if (!locationRepository.existsById(locationId)) {
            throw new RuntimeException("Không tìm thấy địa điểm với ID: " + locationId);
        }

        // Tạo thư mục nếu chưa tồn tại
        String uploadDir = "src/main/resources/static/location/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Tạo tên file duy nhất
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());

        // Lưu thông tin ảnh vào database
        LocationPhotoEntity photo = new LocationPhotoEntity();
        photo.setPhoto_URL("/location/" + fileName);
        photo.setLocation(locationRepository.findById(locationId).get());
        return locationPhotoRepository.save(photo);
    }

    @Override
    public boolean deletePhoto(int photoId) throws IOException {
        if (locationPhotoRepository.existsById(photoId)) {
            LocationPhotoEntity photo = locationPhotoRepository.findById(photoId).get();
            String filePath = "src/main/resources/static" + photo.getPhoto_URL();
            Files.deleteIfExists(Paths.get(filePath));
            locationPhotoRepository.deleteById(photoId);
            return true;
        }
        return false;
    }
}

