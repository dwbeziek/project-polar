package com.cryolytix.backend.dto;

import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.Unit;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SensorDTO {

    private String deviceId;      // The device sending the sensor data
    private SensorType sensorType; // Type of sensor (Temperature, Humidity, etc.)
    private String parameterCode;  // Corresponding code from MQTT data (e.g., 10800)
    private Unit unit;             // Measurement unit (°C, %, V, etc.)
    private BigDecimal value;      // Actual sensor reading

}
