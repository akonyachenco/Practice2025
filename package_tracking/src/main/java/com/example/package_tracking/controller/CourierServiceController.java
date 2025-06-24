package com.example.package_tracking.controller;

import com.example.package_tracking.dto.CourierServiceDto;
import com.example.package_tracking.service.CourierServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/courier-services")
public class CourierServiceController {

    private final CourierServiceService courierServiceService;

    @GetMapping
    public List<CourierServiceDto> findAllCourierServices() {
        log.info("Find all courier services");
        return courierServiceService.findAll();
    }

    @PostMapping("/{id}")
    public Optional<CourierServiceDto> findById(@PathVariable Long id) {
        log.info("Find courier service by id: {}", id);
        return courierServiceService.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public Optional<CourierServiceDto> findByName(@PathVariable String name) {
        log.info("Find courier service by name: {}", name);
        return courierServiceService.findByName(name);
    }

    @GetMapping("/by-email/{email}")
    public Optional<CourierServiceDto> findByEmail(@PathVariable String email) {
        log.info("Find courier service by email: {}", email);
        return courierServiceService.findByEmail(email);
    }

    @GetMapping("/by-phone/{phone}")
    public Optional<CourierServiceDto> findByPhone(@PathVariable String phone) {
        log.info("Find courier service by phone: {}", phone);
        return courierServiceService.findByPhone(phone);
    }

    @PostMapping("create")
    public CourierServiceDto createCourierService(@RequestBody @Valid CourierServiceDto courierServiceDto) {
        log.info("Create courier service: {}", courierServiceDto.getName());
        return courierServiceService.createCourierService(courierServiceDto);
    }

    @PutMapping("update")
    public CourierServiceDto updateCourierService(@RequestBody @Valid CourierServiceDto courierServiceDto) {
        log.info("Update courier service with id: {}", courierServiceDto.getCourierServiceID());
        return courierServiceService.updateCourierService(courierServiceDto);
    }

    @DeleteMapping("delete/{email}")
    public void deleteByEmail(@PathVariable String email) {
        log.info("Delete courier service by email: {}", email);
        courierServiceService.deleteByEmail(email);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Delete courier service by id: {}", id);
        courierServiceService.deleteById(id);
    }

    @DeleteMapping("delete")
    public void deleteCourierService(@RequestBody @Valid CourierServiceDto courierServiceDto) {
        log.info("Delete courier service with id: {}", courierServiceDto.getCourierServiceID());
        courierServiceService.deleteCourierService(courierServiceDto);
    }
}
