package com.example.package_tracking.repository;

import com.example.package_tracking.model.Package;
import com.example.package_tracking.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findAllByUser_UserID(Long userID);
    Optional<Package> findByTrackingNumber(String trackingNumber);
}
