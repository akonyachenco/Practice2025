package com.example.package_tracking.controller;

import com.example.package_tracking.dto.DeliveryStatusDto;
import com.example.package_tracking.model.DeliveryStatus;
import com.example.package_tracking.service.DeliveryStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/delivery-statuses")
public class DeliveryStatusController {

    private final DeliveryStatusService deliveryStatusService;

    @GetMapping
    public List<DeliveryStatusDto> findAllDeliveryStatuses() {
        log.info("Find all delivery statuses");
        return deliveryStatusService.findAll();
    }

    @GetMapping("/{id}")
    public DeliveryStatusDto findById(@PathVariable Long id) {
        log.info("Find delivery status by id: {}", id);
        return deliveryStatusService.findById(id);
    }

    @GetMapping("by-delivery/{deliveryID}")
    public List<DeliveryStatusDto> findAllByDeliveryID(@PathVariable Long deliveryID) {
        log.info("Find all delivery statuses by deliveryID: {}", deliveryID);
        return deliveryStatusService.findAllByDelivery_DeliveryID(deliveryID);
    }

    @GetMapping("by-user/{userID}")
    public List<DeliveryStatusDto> findAllByUserID(@PathVariable Long userID) {
        log.info("Find all delivery statuses by userID: {}", userID);
        return deliveryStatusService.findAllByDelivery_Pkg_User_UserID(userID);
    }

    @GetMapping("/by-status/{status}")
    public List<DeliveryStatusDto> findAllByStatus(@PathVariable String status) {
        log.info("Find all delivery statuses by status: {}", status);
        return deliveryStatusService.findAllByStatus(status);
    }

    @GetMapping("/by-tracking-number/{tracking_number}")
    public DeliveryStatusDto findByPackageTrackingNumber(@PathVariable String tracking_number) {
        log.info("Find delivery status by package tracking number: {}", tracking_number);
        return deliveryStatusService.findByDeliveryPkgTrackingNumber(tracking_number);
    }

    @PostMapping("create")
    public DeliveryStatusDto createDeliveryStatus(@RequestBody DeliveryStatusDto deliveryStatusDto) {
        log.info("Create delivery status: {}", deliveryStatusDto);
        return  deliveryStatusService.createDeliveryStatus(deliveryStatusDto);
    }

    @PutMapping("update")
    public DeliveryStatusDto updateDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        log.info("Update delivery status with id: {}", deliveryStatusDto.getDeliveryStatusID());
        return deliveryStatusService.updateDeliveryStatus(deliveryStatusDto);
    }

    @DeleteMapping("delete")
    public void deleteDeliveryStatus(@RequestBody DeliveryStatusDto deliveryStatusDto) {
        log.info("Delete delivery status with id: {}", deliveryStatusDto.getDeliveryStatusID());
        deliveryStatusService.deleteDeliveryStatus(deliveryStatusDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Delete delivery status by id: {}", id);
        deliveryStatusService.deleteById(id);
    }

    @DeleteMapping("delete/{tracking_number}")
    public void deleteByPackageTrackingNumber(@PathVariable String tracking_number) {
        log.info("Delete delivery status by package tracking number: {}", tracking_number);
        deliveryStatusService.deleteByDeliveryPkgTrackingNumber(tracking_number);
    }
}
