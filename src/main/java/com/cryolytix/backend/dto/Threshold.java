package com.cryolytix.backend.dto;

import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.ThresholdType;
import com.cryolytix.backend.enums.Unit;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Threshold {

    private Long id;
    private Long deviceId;
    private ThresholdType thresholdType;
    private SensorType sensorType;
    private String parameterCode;  // Example: "10800" for temperature
    private Unit unit;
    private BigDecimal minValue;
    private BigDecimal maxValue;
}
