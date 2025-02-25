package com.cryolytix.backend.entities;

import com.cryolytix.backend.dto.Threshold;
import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.ThresholdType;
import com.cryolytix.backend.enums.Unit;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "threshold_t")
@Data
public class ThresholdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceEntity device;

    @Enumerated(EnumType.STRING)
    @Column(name = "sensor_type", nullable = false)
    private SensorType sensorType;  // E.g., TEMPERATURE, HUMIDITY, etc.

    @Enumerated(EnumType.STRING)
    @Column(name = "threshold_type", nullable = false)
    private ThresholdType thresholdType;  // E.g., HIGH, LOW, RANGE, etc.

    @Column(name = "min_value")
    private BigDecimal minValue;

    @Column(name = "max_value")// Minimum threshold value
    private BigDecimal maxValue;  // Maximum threshold value

    @Enumerated(EnumType.STRING)
    private Unit unit;   // E.g., °C, %, V, etc.

    public Threshold toDto() {
        Threshold threshold = new Threshold();
        threshold.setId(id);
        threshold.setDeviceId(device.getId());
        threshold.setSensorType(sensorType);
        threshold.setThresholdType(thresholdType);
        threshold.setMinValue(minValue);
        threshold.setMaxValue(maxValue);
        threshold.setUnit(unit);
        return threshold;
    }
}
