package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.mapping.PackageMapping;
import com.example.package_tracking.dto.mapping.UserMapping;
import com.example.package_tracking.model.User;
import com.example.package_tracking.model.Package;
import com.example.package_tracking.repository.PackageRepository;
import com.example.package_tracking.repository.UserRepository;
import com.example.package_tracking.service.PackageService;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final PackageMapping packageMapping;
    private final UserMapping userMapping;


    @Transactional(readOnly = true)
    public List<PackageDto> findAll() {
        return packageRepository.findAll()
                .stream().map(packageMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<PackageDto> findAllByUser(UserDto user) {
        User findUser = userMapping.toUser(user);
        return packageRepository.findAllByUser(findUser).stream().map(packageMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<PackageDto> findByTrackingNumber(String trackingNumber) {
        return packageRepository.findByTrackingNumber(trackingNumber).map(packageMapping::toDto);
    }

    @Transactional
    public PackageDto createPackage(PackageDto pkg) {
        User user = userRepository.findById(pkg.getUserID()).orElse(null);
        Package newPackage = packageMapping.toPackage(pkg);
        newPackage.setUser(user);
        return packageMapping.toDto(packageRepository.save(newPackage));
    }

    @Transactional
    public PackageDto updatePackage(PackageDto pkg) {
        User user = userRepository.findById(pkg.getUserID()).orElse(null);
        Package newPackage = packageRepository.findById(pkg.getPackageID()).orElse(null);
        newPackage.setTrackingNumber(pkg.getTrackingNumber());
        newPackage.setWeight(pkg.getWeight());
        newPackage.setDimensions(pkg.getDimensions());
        newPackage.setDescription(pkg.getDescription());
        newPackage.setUser(user);
        return packageMapping.toDto(packageRepository.save(newPackage));
    }

    @Transactional
    public void deletePackage(PackageDto pkg) {
        Package findPackage = packageRepository.findById(pkg.getPackageID()).orElse(null);
        packageRepository.delete(findPackage);
    }

}