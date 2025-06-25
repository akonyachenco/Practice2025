package com.example.package_tracking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryStatusDto {
    private Long deliveryStatusID;
    @NotBlank
    private String status;
    private String location;
    private LocalDateTime statusDate;
    @NotBlank
    private Long deliveryID;
}