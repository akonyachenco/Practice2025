package com.example.package_tracking.controller;

import com.example.package_tracking.model.CourierService;
import com.example.package_tracking.service.CourierServiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courier-services")
public class CourierServiceController {

    private final CourierServiceService courierServiceService;

    @GetMapping
    public List<CourierService> findAllCourierServices() {
        return courierServiceService.findAll();
    }

    @GetMapping("/{name}")
    public Optional<CourierService> findByName(@PathVariable String name) {
        return courierServiceService.findByName(name);
    }

    @GetMapping("/{email}")
    public Optional<CourierService> findByEmail(@PathVariable String email) {
        return courierServiceService.findByEmail(email);
    }

    @GetMapping("/{phone}")
    public Optional<CourierService> findByPhone(@PathVariable String phone) {
        return courierServiceService.findByPhone(phone);
    }

    @PostMapping("create-courier-service")
    public CourierService createCourierService(@RequestBody CourierService courierService) {
        return courierServiceService.createCourierService(courierService);
    }

    @PutMapping("update-courier-service")
    public CourierService updateCourierService(@RequestBody CourierService courierService) {
        return courierServiceService.updateCourierService(courierService);
    }

    @DeleteMapping("delete-courier-service/{email}")
    public void deleteByEmail(@PathVariable String email) {
        courierServiceService.deleteByEmail(email);
    }

    @DeleteMapping("delete-courier-service")
    public void deleteCourierService(@RequestBody CourierService courierService) {
        courierServiceService.deleteCourierService(courierService);
    }
}
