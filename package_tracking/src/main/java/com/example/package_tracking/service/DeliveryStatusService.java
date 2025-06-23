package com.example.package_tracking.service;

import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.DeliveryStatus;

import java.util.List;
import java.util.Optional;

public interface DeliveryStatusService {
    List<DeliveryStatus> findAll();
    List<DeliveryStatus> findAllByDelivery(Delivery delivery);
    List<DeliveryStatus> findAllByStatus(String status);
    Optional<DeliveryStatus> findByDeliveryPkgTrackingNumber(String tracking_number);
    DeliveryStatus createDeliveryStatus(DeliveryStatus deliveryStatus);
    DeliveryStatus updateDeliveryStatus(DeliveryStatus deliveryStatus);
    void deleteDeliveryStatus(DeliveryStatus deliveryStatus);
    void deleteByDeliveryPkgTrackingNumber(String tracking_number);
}
