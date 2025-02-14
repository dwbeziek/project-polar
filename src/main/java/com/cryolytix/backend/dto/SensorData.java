package com.cryolytix.backend.dto;

import com.cryolytix.backend.enums.SensorType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SensorData {

    public SensorData(String sensorCodeStr, SensorType sensorType, BigDecimal value, String sensorUnit) {
        this.parameterCode = sensorCodeStr;
        this.sensorType = sensorType;
        this.value = value;
        this.unit = sensorUnit;
    }

    private String parameterCode;  // The actual code from the device (e.g., "10800" for temperature)
    private SensorType sensorType; // Sensor type (e.g., Temperature, Humidity)
    private BigDecimal value;           // Sensor value
    private String unit;            // Unit of measurement (°C, %, V)
}
