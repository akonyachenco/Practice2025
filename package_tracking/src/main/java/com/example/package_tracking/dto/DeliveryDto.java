package com.example.package_tracking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryDto {
    private Long deliveryID;
    @Positive
    private Integer cost;
    private LocalDateTime estimatedDeliveryDate;
    @NotBlank
    private Long packageID;
    @NotBlank
    private Long courierServiceID;
    private List<DeliveryStatusDto> statusHistory;
}
