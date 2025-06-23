package com.example.package_tracking.service;

import com.example.package_tracking.dto.DeliveryStatusDto;


import java.util.List;
import java.util.Optional;

public interface DeliveryStatusService {
    List<DeliveryStatusDto> findAll();
    List<DeliveryStatusDto> findAllByDelivery_DeliveryID(Long deliveryID);
    List<DeliveryStatusDto> findAllByStatus(String status);
    List<DeliveryStatusDto> findAllByDelivery_Pkg_User_UserID(Long userID);
    Optional<DeliveryStatusDto> findById(Long id);
    Optional<DeliveryStatusDto> findByDeliveryPkgTrackingNumber(String tracking_number);
    DeliveryStatusDto createDeliveryStatus(DeliveryStatusDto deliveryStatus);
    DeliveryStatusDto updateDeliveryStatus(DeliveryStatusDto deliveryStatus);
    void deleteDeliveryStatus(DeliveryStatusDto deliveryStatus);
    void deleteByDeliveryPkgTrackingNumber(String tracking_number);
    void deleteById(Long id);
}
