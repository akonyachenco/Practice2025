package com.example.package_tracking.dto;

import lombok.Data;

import java.util.List;

@Data
public class PackageDto {
    private Long packageID;
    private String trackingNumber;
    private Float weight;
    private String dimensions;
    private String description;
    private Long userID;
    private List<DeliveryDto> deliveries;
}
