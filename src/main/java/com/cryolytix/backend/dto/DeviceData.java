package com.cryolytix.backend.dto;


import com.cryolytix.backend.entities.DeviceDataEntity;
import com.cryolytix.backend.entities.SensorDataEntity;
import com.cryolytix.backend.entities.ThresholdEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DeviceData {

    private Long id;
    private Long deviceId;
    private String imei;
    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;
    private int altitude;
    private int angle;
    private int satellites;
    private int speed;

    private List<SensorData> sensorData = new ArrayList<>();;

    public DeviceData() {
    }

}
