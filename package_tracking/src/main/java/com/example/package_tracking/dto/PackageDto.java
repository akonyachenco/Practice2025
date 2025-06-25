package com.example.package_tracking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class PackageDto {
    private Long packageID;
    @NotBlank
    private String trackingNumber;
    @Positive
    private Float weight;
    private String dimensions;
    private String description;
    @NotNull
    private Long userID;
    private List<DeliveryDto> deliveries;
}
