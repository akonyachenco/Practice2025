package com.example.package_tracking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Long packageID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "tracking_number", length = 50, nullable = false)
    private String trackingNumber;

    @Column(name = "weight")
    private float weight;

    @Column(name = "dimensions", length = 30)
    private String dimensions;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "pkg", cascade = CascadeType.ALL)
    private List<Delivery> deliveries = new ArrayList<>();

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        delivery.setPkg(this);
    }
    public void removeDelivery(Delivery delivery) {
        deliveries.remove(delivery);
        delivery.setPkg(null);
    }
}
