package com.example.package_tracking.controller;

import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.DeliveryStatus;
import com.example.package_tracking.service.DeliveryStatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/delivery-statuses")
public class DeliveryStatusController {

    private final DeliveryStatusService deliveryStatusService;

    @GetMapping
    public List<DeliveryStatus> findAllDeliveryStatuses() {
        return deliveryStatusService.findAll();
    }

    @GetMapping("find-by-delivery")
    public List<DeliveryStatus> findAllByDelivery(@RequestBody Delivery delivery) {
        return deliveryStatusService.findAllByDelivery(delivery);
    }

    @GetMapping("/{status}")
    public List<DeliveryStatus> findAllByStatus(@PathVariable String status) {
        return deliveryStatusService.findAllByStatus(status);
    }

    @GetMapping("find-by-tracking/{tracking_number}")
    public Optional<DeliveryStatus> findByPackageTrackingNumber(@PathVariable String tracking_number) {
        return deliveryStatusService.findByDeliveryPkgTrackingNumber(tracking_number);
    }

    @PostMapping("create-delivery-status")
    public DeliveryStatus createDeliveryStatus(@RequestBody DeliveryStatus deliveryStatus) {
        return  deliveryStatusService.createDeliveryStatus(deliveryStatus);
    }

    @PutMapping("update-delivery-status")
    public DeliveryStatus updateDeliveryStatus(DeliveryStatus deliveryStatus) {
        return deliveryStatusService.updateDeliveryStatus(deliveryStatus);
    }

    @DeleteMapping("delete-delivery-status")
    public void deleteDeliveryStatus(@RequestBody DeliveryStatus deliveryStatus) {
        deliveryStatusService.deleteDeliveryStatus(deliveryStatus);
    }

    @DeleteMapping("delete-delivery-status/{tracking_number}")
    public void deleteByPackageTrackingNumber(@PathVariable String tracking_number) {
        deliveryStatusService.deleteByDeliveryPkgTrackingNumber(tracking_number);
    }
}
