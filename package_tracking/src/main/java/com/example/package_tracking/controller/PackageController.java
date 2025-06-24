package com.example.package_tracking.controller;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.service.PackageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/packages")
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public List<PackageDto> findAllPackages() {
        log.info("Find all packages");
        return packageService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PackageDto> findById(@PathVariable Long id) {
        log.info("Find package by id: {}", id);
        return packageService.findById(id);
    }

    @GetMapping("by-user/{userID}")
    public List<PackageDto> findAllByUserID(@PathVariable Long userID) {
        log.info("Find all packages by userID: {}", userID);
        return packageService.findAllByUser_UserID(userID);
    }

    @GetMapping("/by-tracking-number/{trackingNumber}")
    public Optional<PackageDto> findByTrackingNumber(@PathVariable String trackingNumber) {
        log.info("Find package by tracking number: {}", trackingNumber);
        return packageService.findByTrackingNumber(trackingNumber);
    }

    @PostMapping("create")
    public PackageDto createPackage(@RequestBody PackageDto pkgDto) {
        log.info("Create package: {}", pkgDto.getTrackingNumber());
        return packageService.createPackage(pkgDto);
    }

    @PutMapping("update")
    public PackageDto updatePackage(@RequestBody PackageDto pkgDto) {
        log.info("Update package with id: {}", pkgDto.getPackageID());
        return packageService.updatePackage(pkgDto);
    }

    @DeleteMapping("delete")
    public void deletePackage(@RequestBody PackageDto pkgDto) {
        log.info("Delete package with id: {}", pkgDto.getPackageID());
        packageService.deletePackage(pkgDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Delete package by id: {}", id);
        packageService.deleteById(id);
    }
}
