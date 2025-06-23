package com.example.package_tracking.controller;

import com.example.package_tracking.dto.DeliveryStatusDto;
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
    public List<DeliveryStatusDto> findAllDeliveryStatuses() {
        return deliveryStatusService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DeliveryStatusDto> findById(@PathVariable Long id) {
        return deliveryStatusService.findById(id);
    }

    @GetMapping("by-delivery/{deliveryID}")
    public List<DeliveryStatusDto> findAllByDeliveryID(@PathVariable Long deliveryID) {
        return deliveryStatusService.findAllByDelivery_DeliveryID(deliveryID);
    }

    @GetMapping("by-user/{userID}")
    public List<DeliveryStatusDto> findAllByUserID(@PathVariable Long userID) {
        return deliveryStatusService.findAllByDelivery_Pkg_User_UserID(userID);
    }

    @GetMapping("/by-status/{status}")
    public List<DeliveryStatusDto> findAllByStatus(@PathVariable String status) {
        return deliveryStatusService.findAllByStatus(status);
    }

    @GetMapping("/by-tracking-number/{tracking_number}")
    public Optional<DeliveryStatusDto> findByPackageTrackingNumber(@PathVariable String tracking_number) {
        return deliveryStatusService.findByDeliveryPkgTrackingNumber(tracking_number);
    }

    @PostMapping("create")
    public DeliveryStatusDto createDeliveryStatus(@RequestBody DeliveryStatusDto deliveryStatusDto) {
        return  deliveryStatusService.createDeliveryStatus(deliveryStatusDto);
    }

    @PutMapping("update")
    public DeliveryStatusDto updateDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        return deliveryStatusService.updateDeliveryStatus(deliveryStatusDto);
    }

    @DeleteMapping("delete")
    public void deleteDeliveryStatus(@RequestBody DeliveryStatusDto deliveryStatusDto) {
        deliveryStatusService.deleteDeliveryStatus(deliveryStatusDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        deliveryStatusService.deleteById(id);
    }

    @DeleteMapping("delete/{tracking_number}")
    public void deleteByPackageTrackingNumber(@PathVariable String tracking_number) {
        deliveryStatusService.deleteByDeliveryPkgTrackingNumber(tracking_number);
    }
}
