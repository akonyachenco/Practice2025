package com.example.package_tracking.controller;

import com.example.package_tracking.dto.PackageDto;
import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/packages")
public class PackageController {

    private final PackageService packageService;

    @GetMapping
    public List<PackageDto> findAllPackages() {
        return packageService.findAll();
    }

    @GetMapping("find-by-user")
    public List<PackageDto> findAllByUser(@RequestBody UserDto user) {
        return packageService.findAllByUser(user);
    }

    @GetMapping("/{trackingNumber}")
    public Optional<PackageDto> findByTrackingNumber(@PathVariable String trackingNumber) {
        return packageService.findByTrackingNumber(trackingNumber);
    }

    @PostMapping("create-package")
    public PackageDto createPackage(@RequestBody PackageDto pkg) {
        return packageService.createPackage(pkg);
    }

    @PutMapping("update-package")
    public PackageDto updatePackage(@RequestBody PackageDto pkg) {
        return packageService.updatePackage(pkg);
    }

    @DeleteMapping("delete-package")
    public void deletePackage(@RequestBody PackageDto pkg) {
        packageService.deletePackage(pkg);
    }
}
