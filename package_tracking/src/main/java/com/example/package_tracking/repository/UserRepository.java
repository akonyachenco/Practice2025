package com.example.package_tracking.repository;

import com.example.package_tracking.dto.UserDto;
import com.example.package_tracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    void deleteUserByEmail(String email);
//    void deleteByPackages_Deliveries_StatusHistory_Status(String status); - okak


}
