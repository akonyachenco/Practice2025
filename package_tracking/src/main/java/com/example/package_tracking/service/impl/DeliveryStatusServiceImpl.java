package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.DeliveryStatusDto;
import com.example.package_tracking.dto.mapping.DeliveryMapping;
import com.example.package_tracking.dto.mapping.DeliveryStatusMapping;
import com.example.package_tracking.model.Delivery;
import com.example.package_tracking.model.DeliveryStatus;
import com.example.package_tracking.repository.DeliveryRepository;
import com.example.package_tracking.repository.DeliveryStatusRepository;
import com.example.package_tracking.service.DeliveryStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<DeliveryStatusDto> findById(Long id) {
        return deliveryStatusRepository.findById(id)
                .map(deliveryStatusMapping::toDto);
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
    public Optional<DeliveryStatusDto> findByDeliveryPkgTrackingNumber(String tracking_number) {
        return deliveryStatusRepository.findByDeliveryPkgTrackingNumber(tracking_number)
                .map(deliveryStatusMapping::toDto);
    }

    @Transactional
    public DeliveryStatusDto createDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        Delivery delivery = deliveryRepository.findById(deliveryStatusDto.getDeliveryID()).orElse(null);
        DeliveryStatus deliveryStatus = deliveryStatusMapping.toEntity(deliveryStatusDto);
        deliveryStatus.setDelivery(delivery);
        return deliveryStatusMapping.toDto(deliveryStatusRepository.save(deliveryStatus));
    }

    @Transactional
    public DeliveryStatusDto updateDeliveryStatus(DeliveryStatusDto deliveryStatusDto) {
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(deliveryStatusDto.getDeliveryStatusID()).orElse(null);
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
        DeliveryStatus deliveryStatus = deliveryStatusRepository.findById(deliveryStatusDto.getDeliveryStatusID()).orElse(null);
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
