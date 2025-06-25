package com.example.package_tracking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryStatusDto {
    private Long deliveryStatusID;
    @NotBlank
    private String status;
    private String location;
    private LocalDateTime statusDate;
    @NotNull
    private Long deliveryID;
}