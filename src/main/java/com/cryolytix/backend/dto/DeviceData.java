package com.cryolytix.backend.dto;


import com.cryolytix.backend.entities.DeviceDataEntity;
import com.cryolytix.backend.entities.SensorDataEntity;
import com.cryolytix.backend.entities.ThresholdEntity;
import lombok.Data;

import java.time.LocalDateTime;
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

    private List<SensorData> sensorData;

    public DeviceData() {
    }

    public DeviceData(DeviceDataEntity entity) {
        this.id = entity.getId();
        this.deviceId = entity.getDevice().getId();
        this.imei = entity.getDevice().getImei();
        this.timestamp = entity.getTimestamp();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.altitude = entity.getAltitude();
        this.angle = entity.getAngle();
        this.satellites = entity.getSatellites();
        this.speed = entity.getSpeed();
        for (SensorDataEntity sensorDataEntity : entity.getSensorDataEntityList()) {
            this.getSensorData().add(sensorDataEntity.toDto());
        }
    }
}
