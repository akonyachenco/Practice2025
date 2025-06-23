package com.example.package_tracking.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeliveryDto {
    private Long deliveryID;
    private Integer cost;
    private LocalDateTime estimatedDeliveryDate;
    private Long packageID;
    private Long courierServiceID;
    private List<DeliveryStatusDto> statusHistory;
}
