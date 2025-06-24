package com.example.package_tracking.service;

import com.example.package_tracking.dto.PackageDto;

import java.util.Optional;
import java.util.List;

public interface PackageService {
    List<PackageDto> findAll();
    List<PackageDto> findAllByUser_UserID(Long userID);
    PackageDto findById(Long id);
    PackageDto findByTrackingNumber(String trackingNumber);
    PackageDto createPackage(PackageDto pkg);
    PackageDto updatePackage(PackageDto pkg);
    void deletePackage(PackageDto pkg);
    void deleteById(Long id);
}
