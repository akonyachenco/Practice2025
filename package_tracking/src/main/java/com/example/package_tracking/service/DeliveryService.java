package com.example.package_tracking.service;

import com.example.package_tracking.dto.DeliveryDto;



import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    List<DeliveryDto> findAll();
    List<DeliveryDto> findAllByCourierService_CourierServiceID(Long courierServiceID);
    Optional<DeliveryDto> findById(Long id);
    Optional<DeliveryDto> findByPkg_PackageID(Long packageID);
    DeliveryDto createDelivery(DeliveryDto delivery);
    DeliveryDto updateDelivery(DeliveryDto delivery);
    void deleteDelivery(DeliveryDto delivery);
    void deleteByPkg_PackageID(long pkgID);
    void deleteById(Long id);
}
