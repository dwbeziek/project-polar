package com.cryolytix.backend.entities;

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
    private ThresholdType thresholdType;  // SENSOR or DEVICE

    private String parameterCode;  // "10800" for temperature, "21" for speed

    @Enumerated(EnumType.STRING)
    private Unit unit;  // °C, %, V, etc.

    private BigDecimal minValue;
    private BigDecimal maxValue;
}
