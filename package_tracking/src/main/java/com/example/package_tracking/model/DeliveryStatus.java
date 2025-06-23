package com.example.package_tracking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "delivery_status")
public class DeliveryStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_status_id")
    private Long deliveryStatusID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "status_date")
    private LocalDateTime statusDate;
}
