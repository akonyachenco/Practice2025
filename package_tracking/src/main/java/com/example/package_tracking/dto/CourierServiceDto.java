package com.example.package_tracking.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class CourierServiceDto {
    private Long courierServiceID;
    private String name;
    private String phone;
    @Email
    private String email;
    @URL
    private String website;
    private List<DeliveryDto> deliveries;
}
