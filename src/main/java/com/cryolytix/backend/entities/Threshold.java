package com.cryolytix.backend.entities;

import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.ThresholdType;
import com.cryolytix.backend.enums.Unit;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Enumerated(EnumType.STRING)
    private SensorType sensorType;  // E.g., TEMPERATURE, HUMIDITY, etc.

    @Enumerated(EnumType.STRING)
    private ThresholdType thresholdType;  // E.g., HIGH, LOW, RANGE, etc.

    private BigDecimal minValue;  // Minimum threshold value
    private BigDecimal maxValue;  // Maximum threshold value

    @Enumerated(EnumType.STRING)
    private Unit unit;   // E.g., °C, %, V, etc.
}
