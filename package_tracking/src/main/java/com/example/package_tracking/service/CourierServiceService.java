package com.example.package_tracking.service;

import com.example.package_tracking.model.CourierService;

import java.util.List;
import java.util.Optional;

public interface CourierServiceService {
    List<CourierService> findAll();
    Optional<CourierService> findByName(String name);
    Optional<CourierService> findByEmail(String email);
    Optional<CourierService> findByPhone(String phone);
    CourierService createCourierService(CourierService courierService);
    CourierService updateCourierService(CourierService courierService);
    void deleteCourierService(CourierService courierService);
    void deleteByEmail(String email);
}
