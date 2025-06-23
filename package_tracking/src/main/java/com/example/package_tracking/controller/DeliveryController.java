package com.example.package_tracking.controller;

import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.Package;
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
    public List<Delivery> findAllDeliveries() {
        return deliveryService.findAll();
    }

    @GetMapping("find-by-courier-service")
    public List<Delivery> findAllByCourierService(@RequestBody CourierService courierService) {
        return deliveryService.findAllByCourierService(courierService);
    }

    @GetMapping("find-by-package")
    public Optional<Delivery> findByPackage(Package pkg) {
        return deliveryService.findByPkg(pkg);
    }

    @PostMapping("create-delivery")
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @PutMapping("update-delivery")
    public Delivery updateDelivery(@RequestBody Delivery delivery) {
        return deliveryService.updateDelivery(delivery);
    }

    @DeleteMapping("delete-delivery/{pkgID}")
    public void deleteByPackageID(@PathVariable long pkgID) {
        deliveryService.deleteByPkg_PackageID(pkgID);
    }

    @DeleteMapping("delete-delivery")
    public void deleteDelivery(@RequestBody Delivery delivery) {
        deliveryService.deleteDelivery(delivery);
    }

}
