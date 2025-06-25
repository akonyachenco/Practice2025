package com.example.package_tracking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PackageDto {
    private Long packageID;
    @NotBlank
    private String trackingNumber;
    private Float weight;
    private String dimensions;
    private String description;
    @NotBlank
    private Long userID;
    private List<DeliveryDto> deliveries;
}
