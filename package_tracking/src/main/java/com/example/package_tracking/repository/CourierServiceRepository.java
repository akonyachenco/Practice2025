package com.example.package_tracking.repository;

import com.example.package_tracking.model.CourierService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierServiceRepository extends JpaRepository<CourierService, Long> {
    Optional<CourierService> findByName(String name);
    Optional<CourierService> findByEmail(String email);
    Optional<CourierService> findByPhone(String phone);
    void deleteByEmail(String email);
}
