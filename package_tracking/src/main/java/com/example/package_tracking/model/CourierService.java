package com.example.package_tracking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "courier_service")
public class CourierService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_service_id")
    private Long courierServiceID;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "website", length = 255)
    private String website;

    @OneToMany(mappedBy = "courierService", cascade = CascadeType.ALL)
    private List<Delivery> deliveries = new ArrayList<>();

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
        delivery.setCourierService(this);
    }
    public void removeDelivery(Delivery delivery) {
        deliveries.remove(delivery);
        delivery.setCourierService(null);
    }
}
