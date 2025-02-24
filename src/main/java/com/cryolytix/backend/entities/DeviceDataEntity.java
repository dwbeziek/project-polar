package com.cryolytix.backend.entities;

import com.cryolytix.backend.dto.DeviceData;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class DeviceDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceEntity device;

    private LocalDateTime timestamp;
    private double latitude;
    private double longitude;
    private int altitude;
    private int angle;
    private int satellites;
    private int speed;

    @OneToMany(mappedBy = "deviceDataEntity", cascade = CascadeType.ALL)
    private List<SensorDataEntity> sensorDataEntityList;

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

        return deviceData;
    }
}
