package com.cryolytix.backend.entities;

import com.cryolytix.backend.dto.Notification;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private boolean read;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    public Notification toDto() {
        Notification notification = new Notification();
        notification.setId(id);
        notification.setDeviceId(device.getId());
        notification.setMessage(message);
        notification.setRead(read);
        return notification;
    }
}
