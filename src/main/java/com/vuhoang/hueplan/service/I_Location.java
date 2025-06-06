package com.vuhoang.hueplan.service;

import com.vuhoang.hueplan.dto.LocationDTO;
import com.vuhoang.hueplan.entity.LocationPhotoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface I_Location {
    List<LocationDTO> getLocations();
    List<LocationDTO> getLocationsByUser (int userID);
    List<LocationDTO> getLocationsByIsStatus();
    List<LocationDTO> getLocationsByIsStatusIsFalse();
    List<LocationDTO> getLocationsByCategory(int categoryID);
    boolean findByLocation_Name(String location_Name);
    LocationDTO addLocation(LocationDTO locationDTO, List<MultipartFile> files) throws IOException;
    int updateLocation(LocationDTO locationDTO);
    int setIsStatus (int location_ID, boolean isStatus );
    boolean deleteLocation(int location_ID);
    boolean deletePhoto(int photoId)  throws IOException;
    LocationPhotoEntity uploadPhoto(int locationId, MultipartFile file) throws IOException;
    LocationDTO getLocationById(int id);
}
