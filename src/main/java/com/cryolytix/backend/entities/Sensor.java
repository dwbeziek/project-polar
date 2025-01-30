package com.cryolytix.backend.entities;

import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.Unit;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Enumerated(EnumType.STRING)
    private SensorType sensorType;  // e.g., TEMPERATURE_SENSOR_1, HUMIDITY_SENSOR_1

    @Enumerated(EnumType.STRING)
    private Unit unit;  // e.g., °C, %, V

    private BigDecimal value; // The actual real-time value reported by the sensor
}
