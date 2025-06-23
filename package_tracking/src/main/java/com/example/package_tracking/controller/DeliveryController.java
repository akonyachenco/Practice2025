package com.example.package_tracking.controller;

import com.example.package_tracking.dto.DeliveryDto;
import com.example.package_tracking.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public List<DeliveryDto> findAllDeliveries() {
        return deliveryService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DeliveryDto> findById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

    @GetMapping("by-courier-service/{courierServiceID}")
    public List<DeliveryDto> findAllByCourierServiceID(@PathVariable Long courierServiceID) {
        return deliveryService.findAllByCourierService_CourierServiceID(courierServiceID);
    }

    @GetMapping("by-package/{packageID}")
    public Optional<DeliveryDto> findByPackageID(@PathVariable Long packageID) {
        return deliveryService.findByPkg_PackageID(packageID);
    }

    @PostMapping("create")
    public DeliveryDto createDelivery(@RequestBody DeliveryDto deliveryDto) {
        return deliveryService.createDelivery(deliveryDto);
    }

    @PutMapping("update")
    public DeliveryDto updateDelivery(@RequestBody DeliveryDto deliveryDto) {
        return deliveryService.updateDelivery(deliveryDto);
    }

    @DeleteMapping("delete/{pkgID}")
    public void deleteByPackageID(@PathVariable long pkgID) {
        deliveryService.deleteByPkg_PackageID(pkgID);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        deliveryService.deleteById(id);
    }

    @DeleteMapping("delete")
    public void deleteDelivery(@RequestBody DeliveryDto deliveryDto) {
        deliveryService.deleteDelivery(deliveryDto);
    }

}
