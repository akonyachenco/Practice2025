package com.example.package_tracking.service;

import com.example.package_tracking.dto.CourierServiceDto;
import com.example.package_tracking.model.CourierService;

import java.util.List;
import java.util.Optional;

public interface CourierServiceService {
    List<CourierServiceDto> findAll();
    Optional<CourierServiceDto> findById(Long id);
    Optional<CourierServiceDto> findByName(String name);
    Optional<CourierServiceDto> findByEmail(String email);
    Optional<CourierServiceDto> findByPhone(String phone);
    CourierServiceDto createCourierService(CourierServiceDto courierService);
    CourierServiceDto updateCourierService(CourierServiceDto courierService);
    void deleteCourierService(CourierServiceDto courierService);
    void deleteByEmail(String email);
    void deleteById(Long id);
}
