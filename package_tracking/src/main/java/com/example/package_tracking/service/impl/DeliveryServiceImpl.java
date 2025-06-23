package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.DeliveryDto;
import com.example.package_tracking.dto.mapping.DeliveryMapping;
import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.model.Package;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.repository.CourierServiceRepository;
import com.example.package_tracking.repository.DeliveryRepository;
import com.example.package_tracking.repository.PackageRepository;
import com.example.package_tracking.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapping deliveryMapping;
    private final CourierServiceRepository courierServiceRepository;
    private final PackageRepository packageRepository;

    @Transactional(readOnly = true)
    public List<DeliveryDto> findAll() {
        return deliveryRepository.findAll()
                .stream().map(deliveryMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<DeliveryDto> findById(Long id) {
        return deliveryRepository.findById(id)
                .map(deliveryMapping::toDto);
    }

    @Transactional(readOnly = true)
    public List<DeliveryDto> findAllByCourierService_CourierServiceID(Long courierServiceID) {
        return deliveryRepository.findAllByCourierService_CourierServiceID(courierServiceID)
                .stream().map(deliveryMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<DeliveryDto> findByPkg_PackageID(Long packageID) {
        return deliveryRepository.findByPkg_PackageID(packageID)
                .map(deliveryMapping::toDto);
    }

    @Transactional
    public DeliveryDto createDelivery(DeliveryDto deliveryDto) {
        CourierService courierService = courierServiceRepository.findById(deliveryDto.getCourierServiceID()).orElse(null);
        Package pkg = packageRepository.findById(deliveryDto.getPackageID()).orElse(null);
        Delivery delivery = deliveryMapping.toEntity(deliveryDto);
        delivery.setCourierService(courierService);
        delivery.setPkg(pkg);
        return deliveryMapping.toDto(deliveryRepository.save(delivery));
    }

    @Transactional
    public DeliveryDto updateDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryRepository.findById(deliveryDto.getDeliveryID()).orElse(null);
        if(deliveryDto.getCost() != null)
            delivery.setCost(deliveryDto.getCost());
        if(deliveryDto.getEstimatedDeliveryDate() != null)
            delivery.setEstimatedDeliveryDate(deliveryDto.getEstimatedDeliveryDate());


        if(deliveryDto.getCourierServiceID() != null) {
            CourierService courierService = courierServiceRepository.findById(deliveryDto.getCourierServiceID()).orElse(null);
            delivery.setCourierService(courierService);
        }
        if(deliveryDto.getPackageID() != null) {
            Package pkg = packageRepository.findById(deliveryDto.getPackageID()).orElse(null);
            delivery.setPkg(pkg);
        }
        return deliveryMapping.toDto(deliveryRepository.save(delivery));
    }

    @Transactional
    public void deleteDelivery(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryRepository.findById(deliveryDto.getDeliveryID()).orElse(null);
        deliveryRepository.delete(delivery);
    }

    @Transactional
    public void deleteByPkg_PackageID(long pkgID) {
        deliveryRepository.deleteByPkg_PackageID(pkgID);
    }

    @Transactional
    public  void deleteById(Long id) {
        deliveryRepository.deleteById(id);
    }
}
