package com.example.package_tracking.service;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.model.Package;
import com.example.package_tracking.model.User;

import java.util.Optional;
import java.util.List;

public interface PackageService {
    List<PackageDto> findAll();
    List<PackageDto> findAllByUser(UserDto user);
    Optional<PackageDto> findByTrackingNumber(String trackingNumber);
    PackageDto createPackage(PackageDto pkg);
    PackageDto updatePackage(PackageDto pkg);
    void deletePackage(PackageDto pkg);
}
