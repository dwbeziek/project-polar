package com.cryolytix.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String imei;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Threshold> thresholds;

//    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
//    private List<Notification> notifications;
}
