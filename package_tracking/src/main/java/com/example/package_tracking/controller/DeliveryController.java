package com.example.package_tracking.controller;

import com.example.package_tracking.dto.DeliveryDto;
import com.example.package_tracking.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryDto> findAllDeliveries() {
        log.info("Find all deliveries");
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryDto findById(@PathVariable Long id) {
        log.info("Find delivery by id: {}", id);
        return deliveryService.findById(id);
    }

    @GetMapping("by-courier-service/{courierServiceID}")
    public List<DeliveryDto> findAllByCourierServiceID(@PathVariable Long courierServiceID) {
        log.info("Find all deliveries by courier service id: {}", courierServiceID);
        return deliveryService.findAllByCourierService_CourierServiceID(courierServiceID);
    }

    @GetMapping("by-package/{packageID}")
    public DeliveryDto findByPackageID(@PathVariable Long packageID) {
        log.info("Find delivery by package id: {}", packageID);
        return deliveryService.findByPkg_PackageID(packageID);
    }

    @PostMapping("create")
    public DeliveryDto createDelivery(@RequestBody @Valid DeliveryDto deliveryDto) {
        log.info("Create delivery: {}", deliveryDto);
        return deliveryService.createDelivery(deliveryDto);
    }

    @PutMapping("update")
    public DeliveryDto updateDelivery(@RequestBody @Valid DeliveryDto deliveryDto) {
        log.info("Update delivery with id: {}", deliveryDto.getDeliveryID());
        return deliveryService.updateDelivery(deliveryDto);
    }

    @DeleteMapping("delete/by-packageID/{pkgID}")
    public void deleteByPackageID(@PathVariable long pkgID) {
        log.info("Delete delivery by package id: {}", pkgID);
        deliveryService.deleteByPkg_PackageID(pkgID);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Delete delivery by id: {}", id);
        deliveryService.deleteById(id);
    }

    @DeleteMapping("delete")
    public void deleteDelivery(@RequestBody @Valid DeliveryDto deliveryDto) {
        log.info("Delete delivery with id: {}", deliveryDto.getDeliveryID());
        deliveryService.deleteDelivery(deliveryDto);
    }

}
