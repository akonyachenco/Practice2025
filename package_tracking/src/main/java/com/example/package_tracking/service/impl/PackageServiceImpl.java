package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.mapping.PackageMapping;
import com.example.package_tracking.dto.mapping.UserMapping;
import com.example.package_tracking.exception.EntityNotFoundException;
import com.example.package_tracking.model.User;
import com.example.package_tracking.model.Package;
import com.example.package_tracking.repository.PackageRepository;
import com.example.package_tracking.repository.UserRepository;
import com.example.package_tracking.service.PackageService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final PackageMapping packageMapping;


    @Transactional(readOnly = true)
    public List<PackageDto> findAll() {
        return packageRepository.findAll()
                .stream().map(packageMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public PackageDto findById(Long id) {
        return packageRepository.findById(id).map(packageMapping::toDto)
                .orElseThrow(() -> {
                    log.warn("Package not found with id {}", id);
                    return EntityNotFoundException.create("Package not found with id", id);
                });
    }

    @Transactional(readOnly = true)
    public List<PackageDto> findAllByUser_UserID(Long userID) {
        return packageRepository.findAllByUser_UserID(userID).stream().map(packageMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public PackageDto findByTrackingNumber(String tracking_number) {
        return packageRepository.findByTrackingNumber(tracking_number).map(packageMapping::toDto)
                .orElseThrow(() -> {
                    log.warn("Package not found with tracking_number {}", tracking_number);
                    return EntityNotFoundException.create("Package not found with tracking_number", tracking_number);
                });
    }

    @Transactional
    public PackageDto createPackage(PackageDto pkg) {
        User user = userRepository.findById(pkg.getUserID())
                .orElseThrow(() -> {
                    log.warn("User not found with id {}", pkg.getUserID());
                    return EntityNotFoundException.create("User not found with id", pkg.getUserID());
                });
        Package newPackage = packageMapping.toEntity(pkg);
        newPackage.setUser(user);
        return packageMapping.toDto(packageRepository.save(newPackage));
    }

    @Transactional
    public PackageDto updatePackage(PackageDto pkg) {
        Package newPackage = packageRepository.findById(pkg.getPackageID())
                .orElseThrow(() -> {
                    log.warn("Package not found with id {}", pkg.getPackageID());
                    return EntityNotFoundException.create("Package not found with id", pkg.getPackageID());
                });
        if(pkg.getTrackingNumber() != null)
            newPackage.setTrackingNumber(pkg.getTrackingNumber());
        if(pkg.getWeight() != null)
            newPackage.setWeight(pkg.getWeight());
        if(pkg.getDimensions() != null)
            newPackage.setDimensions(pkg.getDimensions());
        if(pkg.getDescription() != null)
            newPackage.setDescription(pkg.getDescription());

        if(pkg.getUserID() != null) {
            User user = userRepository.findById(pkg.getUserID()).orElse(null);
            newPackage.setUser(user);
        }
        return packageMapping.toDto(packageRepository.save(newPackage));
    }

    @Transactional
    public void deletePackage(PackageDto pkg) {
        Package findPackage = packageRepository.findById(pkg.getPackageID())
                .orElseThrow(() -> {
                    log.warn("Package not found with id {}", pkg.getPackageID());
                    return EntityNotFoundException.create("Package not found with id", pkg.getPackageID());
                });
        packageRepository.delete(findPackage);
    }

    @Transactional
    public void deleteById(Long id) {
        packageRepository.deleteById(id);
    }

}