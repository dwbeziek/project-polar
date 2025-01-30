package com.cryolytix.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceDTO {

    private String deviceId;
    private long timestamp;
    private int pr;
    private String latlng;
    private int altitude;
    private int angle;
    private int satellites;
    private int speed;
    private int eventCode;

    private List<SensorDTO> sensors; // ✅ Added sensors inside DeviceDTO

}
