package com.example.package_tracking.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourierServiceDto {
    private Long courierServiceID;
    private String name;
    private String phone;
    private String email;
    private String website;
    private List<DeliveryDto> deliveries;
}
