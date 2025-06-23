package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.dto.mapping.PackageMapping;
import com.example.package_tracking.dto.mapping.UserMapping;
import com.example.package_tracking.model.User;
import com.example.package_tracking.model.Package;
import com.example.package_tracking.repository.PackageRepository;
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
        Package newPackage = packageMapping.toPackage(pkg);
        packageRepository.save(newPackage);
        return pkg;
    }

    @Transactional
    public PackageDto updatePackage(PackageDto pkg) {
        Package newPackage = packageMapping.toPackage(pkg);
        packageRepository.save(newPackage);
        return pkg;
    }

    @Transactional
    public void deletePackage(PackageDto pkg) {
        packageRepository.delete(packageMapping.toPackage(pkg));
    }

}
