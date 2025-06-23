package com.example.package_tracking.service.impl;

import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.model.Package;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.repository.DeliveryRepository;
import com.example.package_tracking.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional(readOnly = true)
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Delivery> findAllByCourierService(CourierService courierService) {
        return deliveryRepository.findAllByCourierService(courierService);
    }

    @Transactional(readOnly = true)
    public Optional<Delivery> findByPkg(Package pkg) {
        return deliveryRepository.findByPkg(pkg);
    }

    @Transactional
    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Transactional
    public void deleteDelivery(Delivery delivery) {
        deliveryRepository.delete(delivery);
    }

    @Transactional
    public void deleteByPkg_PackageID(long pkgID) {
        deliveryRepository.deleteByPkg_PackageID(pkgID);
    }
}
