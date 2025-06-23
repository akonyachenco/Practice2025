package com.example.package_tracking.service.impl;

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


    @Transactional(readOnly = true)
    public List<CourierService> findAll() {
        return courierServiceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<CourierService> findByName(String name) {
        return courierServiceRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<CourierService> findByEmail(String email) {
        return courierServiceRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<CourierService> findByPhone(String phone) {
        return courierServiceRepository.findByPhone(phone);
    }

    @Transactional
    public CourierService createCourierService(CourierService courierService) {
        return courierServiceRepository.save(courierService);
    }

    @Transactional
    public CourierService updateCourierService(CourierService courierService) {
        return courierServiceRepository.save(courierService);
    }

    @Transactional
    public void deleteCourierService(CourierService courierService) {
        courierServiceRepository.delete(courierService);
    }

    @Transactional
    public void deleteByEmail(String email) {
        courierServiceRepository.deleteByEmail(email);
    }
}
