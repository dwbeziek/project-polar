package com.cryolytix.backend.entities;

import com.cryolytix.backend.enums.SensorType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_data_id", nullable = false)
    private DeviceData deviceData;

    private String parameterCode; // The actual sensor parameter code (e.g., "10800" for temperature)

    @Enumerated(EnumType.STRING)
    private SensorType sensorType; // Temperature, Humidity, Movement, Battery, etc.

    private BigDecimal value; // Sensor value (temperature, humidity, etc.)

    private String unit; // °C, %, V, etc.


}
