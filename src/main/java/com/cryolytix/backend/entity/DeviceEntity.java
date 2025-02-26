package com.cryolytix.backend.entity;

import com.cryolytix.backend.dto.Device;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "device_t")
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

    public Device toDto() {
        Device device = new Device();
        device.setId(id);
        device.setImei(imei);
        device.setCode(code);
        device.setName(name);
        device.setDescription(description);
        return device;
    }
}
