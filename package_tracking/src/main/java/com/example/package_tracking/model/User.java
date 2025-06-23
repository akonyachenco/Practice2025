package com.example.package_tracking.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 255)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Package> packages = new ArrayList<>();

    public void addPackage(Package pkg) {
        packages.add(pkg);
        pkg.setUser(this);
    }
    public void removePackage(Package pkg) {
        packages.remove(pkg);
        pkg.setUser(null);
    }

}
