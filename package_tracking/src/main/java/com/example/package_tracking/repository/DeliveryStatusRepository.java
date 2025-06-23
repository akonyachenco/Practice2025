package com.example.package_tracking.repository;

import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Long> {
    List<DeliveryStatus> findAllByDelivery(Delivery delivery);
    List<DeliveryStatus> findAllByStatus(String status);
    Optional<DeliveryStatus> findByDeliveryPkgTrackingNumber(String tracking_number);
    void deleteByDeliveryPkgTrackingNumber(String tracking_number);
}
