package com.cryolytix.backend.dto;

import lombok.Data;

@Data
public class DeviceDTO {

    private String deviceId;
    private String name;
    private String description;
    private double thresholdTemperature;

}
