package com.cryolytix.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;
    private int altitude;
    private int angle;
    private int satellites;
    private int speed;

    @OneToMany(mappedBy = "deviceData", cascade = CascadeType.ALL)
    private List<SensorData> sensorDataList;
}
