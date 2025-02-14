package com.cryolytix.backend.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DeviceData {

    private String imei;
    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;
    private int altitude;
    private int angle;
    private int satellites;
    private int speed;

    private List<SensorData> sensorData;
}
