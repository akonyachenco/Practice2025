package com.example.package_tracking.repository;

import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByCourierService(CourierService courierService);
    Optional<Delivery> findByPkg(Package pkg);
    void deleteByPkg_PackageID(long pkgID);
}
