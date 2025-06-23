package com.example.package_tracking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private long deliveryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Package pkg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_service_id", nullable = false)
    private CourierService courierService;

    @Column(name = "cost")
    private int cost;

    @Column(name = "estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<DeliveryStatus> statusHistory = new ArrayList<>();

    public void addStatusHistory(DeliveryStatus status) {
        statusHistory.add(status);
        status.setDelivery(this);
    }
    public void removeStatusHistory(DeliveryStatus status) {
        statusHistory.remove(status);
        status.setDelivery(null);
    }

}
