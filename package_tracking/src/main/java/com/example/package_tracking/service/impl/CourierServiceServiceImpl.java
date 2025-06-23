package com.example.package_tracking.service.impl;

import com.example.package_tracking.dto.CourierServiceDto;
import com.example.package_tracking.dto.mapping.CourierServiceMapping;
import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.repository.CourierServiceRepository;
import com.example.package_tracking.service.CourierServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourierServiceServiceImpl implements CourierServiceService {

    private final CourierServiceRepository courierServiceRepository;
    private final CourierServiceMapping courierServiceMapping;


    @Transactional(readOnly = true)
    public List<CourierServiceDto> findAll() {
        return courierServiceRepository.findAll()
                .stream().map(courierServiceMapping::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<CourierServiceDto> findById(Long id) {
        return courierServiceRepository.findById(id)
                .map(courierServiceMapping::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<CourierServiceDto> findByName(String name) {
        return courierServiceRepository.findByName(name)
                .map(courierServiceMapping::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<CourierServiceDto> findByEmail(String email) {
        return courierServiceRepository.findByEmail(email)
                .map(courierServiceMapping::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<CourierServiceDto> findByPhone(String phone) {
        return courierServiceRepository.findByPhone(phone)
                .map(courierServiceMapping::toDto);
    }

    @Transactional
    public CourierServiceDto createCourierService(CourierServiceDto courierServiceDto) {
        CourierService courierService = courierServiceMapping.toEntity(courierServiceDto);
        return courierServiceMapping.toDto(courierServiceRepository.save(courierService));
    }

    @Transactional
    public CourierServiceDto updateCourierService(CourierServiceDto courierServiceDto) {
        CourierService courierService = courierServiceRepository.findById(courierServiceDto.getCourierServiceID()).orElse(null);
        if(courierServiceDto.getName() != null)
            courierService.setName(courierServiceDto.getName());
        if(courierServiceDto.getPhone() != null)
            courierService.setPhone(courierServiceDto.getPhone());
        if(courierServiceDto.getEmail() != null)
            courierService.setEmail(courierServiceDto.getEmail());
        if(courierServiceDto.getWebsite() != null)
            courierService.setWebsite(courierServiceDto.getWebsite());
        return courierServiceMapping.toDto(courierServiceRepository.save(courierService));
    }

    @Transactional
    public void deleteCourierService(CourierServiceDto courierServiceDto) {
        CourierService courierService = courierServiceRepository.findById(courierServiceDto.getCourierServiceID()).orElse(null);
        courierServiceRepository.delete(courierService);
    }

    @Transactional
    public void deleteByEmail(String email) {
        courierServiceRepository.deleteByEmail(email);
    }

    @Transactional
    public  void deleteById(Long id) {
        courierServiceRepository.deleteById(id);
    }
}
