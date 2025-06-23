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


    @Transactional(readOnly = true)
    public List<PackageDto> findAll() {
        return packageRepository.findAll()
                .stream().map(packageMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<PackageDto> findById(Long id) {
        return packageRepository.findById(id).map(packageMapping::toDto);
    }

    @Transactional(readOnly = true)
    public List<PackageDto> findAllByUser_UserID(Long userID) {
        return packageRepository.findAllByUser_UserID(userID).stream().map(packageMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<PackageDto> findByTrackingNumber(String trackingNumber) {
        return packageRepository.findByTrackingNumber(trackingNumber).map(packageMapping::toDto);
    }

    @Transactional
    public PackageDto createPackage(PackageDto pkg) {
        User user = userRepository.findById(pkg.getUserID()).orElse(null);
        Package newPackage = packageMapping.toEntity(pkg);
        newPackage.setUser(user);
        return packageMapping.toDto(packageRepository.save(newPackage));
    }

    @Transactional
    public PackageDto updatePackage(PackageDto pkg) {
        Package newPackage = packageRepository.findById(pkg.getPackageID()).orElse(null);
        if(pkg.getTrackingNumber() != null)
            newPackage.setTrackingNumber(pkg.getTrackingNumber());
        if(pkg.getWeight() != null)
            newPackage.setWeight(pkg.getWeight());
        if(pkg.getDescription() != null)
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
        Package findPackage = packageRepository.findById(pkg.getPackageID()).orElse(null);
        packageRepository.delete(findPackage);
    }

    @Transactional
    public void deleteById(Long id) {
        packageRepository.deleteById(id);
    }

}