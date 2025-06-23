package com.example.package_tracking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryStatusDto {
    private Long deliveryStatusID;
    private String status;
    private String location;
    private LocalDateTime statusDate;
    private Long deliveryID;
}