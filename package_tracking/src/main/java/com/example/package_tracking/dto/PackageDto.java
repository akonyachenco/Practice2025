package com.example.package_tracking.dto;

import lombok.Data;

@Data
public class PackageDto {
    private long packageID;
    private String trackingNumber;
    private float weight;
    private String dimensions;
    private String description;
    private long userID;
}
