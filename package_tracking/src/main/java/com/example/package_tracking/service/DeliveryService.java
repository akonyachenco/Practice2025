package com.example.package_tracking.service;

import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.Package;


import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<Delivery> findAll();
    List<Delivery> findAllByCourierService(CourierService courierService);
    Optional<Delivery> findByPkg(Package pkg);
    Delivery createDelivery(Delivery delivery);
    Delivery updateDelivery(Delivery delivery);
    void deleteDelivery(Delivery delivery);
    void deleteByPkg_PackageID(long pkgID);
}
