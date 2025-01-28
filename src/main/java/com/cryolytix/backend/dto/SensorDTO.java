package com.cryolytix.backend.dto;

import lombok.Data;

@Data
public class SensorDTO {

    private String sensorType;
    private String unit;
    private double value;
    private String deviceId;

}
