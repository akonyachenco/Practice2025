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

    @GetMapping("/{id}")
    public Optional<PackageDto> findById(@PathVariable Long id) {
        return packageService.findById(id);
    }

    @GetMapping("by-user/{userID}")
    public List<PackageDto> findAllByUserID(@PathVariable Long userID) {
        return packageService.findAllByUser_UserID(userID);
    }

    @GetMapping("/by-tracking-number/{trackingNumber}")
    public Optional<PackageDto> findByTrackingNumber(@PathVariable String trackingNumber) {
        return packageService.findByTrackingNumber(trackingNumber);
    }

    @PostMapping("create")
    public PackageDto createPackage(@RequestBody PackageDto pkgDto) {
        return packageService.createPackage(pkgDto);
    }

    @PutMapping("update")
    public PackageDto updatePackage(@RequestBody PackageDto pkgDto) {
        return packageService.updatePackage(pkgDto);
    }

    @DeleteMapping("delete")
    public void deletePackage(@RequestBody PackageDto pkgDto) {
        packageService.deletePackage(pkgDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        packageService.deleteById(id);
    }
}
