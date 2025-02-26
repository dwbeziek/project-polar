package com.cryolytix.backend.entity;

import com.cryolytix.backend.dto.DeviceData;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "device_data_t")
@Data
public class DeviceDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceEntity device;

    @Column(nullable = false)
    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;
    private int altitude;
    private int angle;
    private int satellites;
    private int speed;

    @OneToMany(mappedBy = "deviceDataEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorDataEntity> sensorDataEntityList = new ArrayList<>();

    public DeviceData toDto() {
        DeviceData deviceData = new DeviceData();
        deviceData.setId(id);
        deviceData.setDeviceId(device.getId());
        deviceData.setImei(device.getImei());
        deviceData.setTimestamp(timestamp);
        deviceData.setLatitude(latitude);
        deviceData.setLongitude(longitude);
        deviceData.setAltitude(altitude);
        deviceData.setAngle(angle);
        deviceData.setSatellites(satellites);
        deviceData.setSpeed(speed);
        deviceData.setSensorData(sensorDataEntityList.stream().map(SensorDataEntity::toDto).collect(Collectors.toList()));

        return deviceData;
    }
}
