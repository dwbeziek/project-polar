package com.cryolytix.backend.entities;

import com.cryolytix.backend.dto.Device;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String imei;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThresholdEntity> thresholdEntities= new ArrayList<>();

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<NotificationEntity> notifications = new ArrayList<>();

    public Device toDto() {
        Device device = new Device();
        device.setId(id);
        device.setImei(imei);
        device.setCode(code);
        device.setName(name);
        device.setDescription(description);

        for (ThresholdEntity thresholdEntity : thresholdEntities) {
            device.getThresholds().add(thresholdEntity.toDto());
        }

        for (NotificationEntity notificationEntity : notifications) {
            device.getNotifications().add(notificationEntity.toDto());
        }


        return device;
    }
}
