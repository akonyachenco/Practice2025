package com.example.package_tracking.service.impl;

import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.DeliveryStatus;
import com.example.package_tracking.repository.DeliveryStatusRepository;
import com.example.package_tracking.service.DeliveryStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryStatusServiceImpl implements DeliveryStatusService {

    private final DeliveryStatusRepository deliveryStatusRepository;

    @Transactional(readOnly = true)
    public List<DeliveryStatus> findAll() {
        return deliveryStatusRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<DeliveryStatus> findAllByDelivery(Delivery delivery) {
        return deliveryStatusRepository.findAllByDelivery(delivery);
    }

    @Transactional(readOnly = true)
    public List<DeliveryStatus> findAllByStatus(String status) {
        return deliveryStatusRepository.findAllByStatus(status);
    }

    @Transactional(readOnly = true)
    public Optional<DeliveryStatus> findByDeliveryPkgTrackingNumber(String tracking_number) {
        return deliveryStatusRepository.findByDeliveryPkgTrackingNumber(tracking_number);
    }

    @Transactional
    public DeliveryStatus createDeliveryStatus(DeliveryStatus deliveryStatus) {
        return deliveryStatusRepository.save(deliveryStatus);
    }

    @Transactional
    public DeliveryStatus updateDeliveryStatus(DeliveryStatus deliveryStatus) {
        return deliveryStatusRepository.save(deliveryStatus);
    }

    @Transactional
    public void deleteDeliveryStatus(DeliveryStatus deliveryStatus) {
        deliveryStatusRepository.delete(deliveryStatus);
    }

    @Transactional
    public void deleteByDeliveryPkgTrackingNumber(String tracking_number) {
        deliveryStatusRepository.deleteByDeliveryPkgTrackingNumber(tracking_number);
    }
}
