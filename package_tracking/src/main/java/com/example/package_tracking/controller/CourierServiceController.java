package com.example.package_tracking.controller;

import com.example.package_tracking.dto.CourierServiceDto;
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
    public List<CourierServiceDto> findAllCourierServices() {
        return courierServiceService.findAll();
    }

    @PostMapping("/{id}")
    public Optional<CourierServiceDto> findById(@PathVariable Long id) {
        return courierServiceService.findById(id);
    }

    @GetMapping("/by-name/{name}")
    public Optional<CourierServiceDto> findByName(@PathVariable String name) {
        return courierServiceService.findByName(name);
    }

    @GetMapping("/by-email/{email}")
    public Optional<CourierServiceDto> findByEmail(@PathVariable String email) {
        return courierServiceService.findByEmail(email);
    }

    @GetMapping("/by-phone/{phone}")
    public Optional<CourierServiceDto> findByPhone(@PathVariable String phone) {
        return courierServiceService.findByPhone(phone);
    }

    @PostMapping("create")
    public CourierServiceDto createCourierService(@RequestBody CourierServiceDto courierServiceDto) {
        return courierServiceService.createCourierService(courierServiceDto);
    }

    @PutMapping("update")
    public CourierServiceDto updateCourierService(@RequestBody CourierServiceDto courierServiceDto) {
        return courierServiceService.updateCourierService(courierServiceDto);
    }

    @DeleteMapping("delete/{email}")
    public void deleteByEmail(@PathVariable String email) {
        courierServiceService.deleteByEmail(email);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        courierServiceService.deleteById(id);
    }

    @DeleteMapping("delete")
    public void deleteCourierService(@RequestBody CourierServiceDto courierServiceDto) {
        courierServiceService.deleteCourierService(courierServiceDto);
    }
}
