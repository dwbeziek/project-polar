package com.cryolytix.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorType;
    private String unit;
    private double value;

    @ManyToOne
    private Device device;
}
