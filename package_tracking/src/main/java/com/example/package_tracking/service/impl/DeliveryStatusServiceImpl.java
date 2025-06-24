package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.DeliveryStatusDto;
import com.example.package_tracking.dto.mapping.DeliveryMapping;
import com.example.package_tracking.dto.mapping.DeliveryStatusMapping;
import com.example.package_tracking.exception.EntityNotFoundException;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.DeliveryStatus;
import com.example.package_tracking.repository.DeliveryRepository;
import com.example.package_tracking.repository.DeliveryStatusRepository;
import com.example.package_tracking.service.DeliveryStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryStatusServiceImpl implements DeliveryStatusService {

    private final DeliveryStatusRepository deliveryStatusRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryStatusMapping deliveryStatusMapping;

    @Transactional(readOnly = true)
    public List<DeliveryStatusDto> findAll() {
        return deliveryStatusRepository.findAll()
                .stream().map(deliveryStatusMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public DeliveryStatusDto findById(Long id) {
        return deliveryStatusRepository.findById(id)
                .map(deliveryStatusMapping::toDto)
                .orElseThrow(() -> {
                    log.warn("Delivery status not found with id {}", id);
                    return EntityNotFoundException.create("Delivery status not found with id", id);
                        });
    }

    @Transactional(readOnly = true)
    public List<DeliveryStatusDto> findAllByDelivery_DeliveryID(Long deliveryID) {
        return deliveryStatusRepository.findAllByDelivery_DeliveryID(deliveryID)
                .stream().map(deliveryStatusMapping::toDto).toList();
    }

    @Transactional
    public List<DeliveryStatusDto> findAllByDelivery_Pkg_User_UserID(Long userID) {
        return deliveryStatusRepository.findAllByDelivery_Pkg_User_UserID(userID)
                .stream().map(deliveryStatusMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<DeliveryStatusDto> findAllByStatus(String status) {
        return deliveryStatusRepository.findAllByStatus(status)
                .stream().map(deliveryStatusMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public DeliveryStatusDto findByDeliveryPkgTrackingNumber(String tracking_number) {
        return deliveryStatusRepository.findByDeliveryPkgTrackingNumber(tracking_number)
                .map(deliveryStatusMapping::toDto)
                .orElseThrow(() -> {
                    log.warn("Delivery status not found with tracking_number {}", tracking_number);
                    return EntityNotFoundException.create("Delivery status not found with tracking_number", tracking_number);
                });
    }

    @Transactional
    public DeliveryStatusDto createDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        Delivery delivery = deliveryRepository.findById(deliveryStatusDto.getDeliveryID())
                .orElseThrow(() -> {
                    log.warn("Delivery not found with id {}", deliveryStatusDto.getDeliveryID());
                    return EntityNotFoundException.create("Delivery not found with id", deliveryStatusDto.getDeliveryID());
                });
        DeliveryStatus deliveryStatus = deliveryStatusMapping.toEntity(deliveryStatusDto);
        deliveryStatus.setDelivery(delivery);
        return deliveryStatusMapping.toDto(deliveryStatusRepository.save(deliveryStatus));
    }

    @Transactional
    public DeliveryStatusDto updateDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(deliveryStatusDto.getDeliveryStatusID())
                .orElseThrow(() -> {
                    log.warn("Delivery status not found with id {}", deliveryStatusDto.getDeliveryStatusID());
                    return EntityNotFoundException.create("Delivery status not found with id", deliveryStatusDto.getDeliveryStatusID());
                });
        if(deliveryStatusDto.getDeliveryStatusID() != null)
            deliveryStatus.setStatus(deliveryStatusDto.getStatus());
        if(deliveryStatusDto.getStatus() != null)
            deliveryStatus.setStatusDate(deliveryStatusDto.getStatusDate());
        if(deliveryStatusDto.getLocation() != null)
            deliveryStatus.setLocation(deliveryStatusDto.getLocation());

        if(deliveryStatusDto.getDeliveryID() != null) {
            Delivery delivery = deliveryRepository.findById(deliveryStatusDto.getDeliveryID()).orElse(null);
            deliveryStatus.setDelivery(delivery);
        }
        return deliveryStatusMapping.toDto(deliveryStatusRepository.save(deliveryStatus));
    }

    @Transactional
    public void deleteDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(deliveryStatusDto.getDeliveryStatusID())
                .orElseThrow(() -> {
                    log.warn("Delivery status not found with id {}", deliveryStatusDto.getDeliveryStatusID());
                    return EntityNotFoundException.create("Delivery status not found with id", deliveryStatusDto.getDeliveryStatusID());
                });
        deliveryStatusRepository.delete(deliveryStatus);
    }

    @Transactional
    public void deleteByDeliveryPkgTrackingNumber(String tracking_number) {
        deliveryStatusRepository.deleteByDeliveryPkgTrackingNumber(tracking_number);
    }

    @Transactional
    public  void deleteById(Long id) {
        deliveryStatusRepository.deleteById(id);
    }

}
