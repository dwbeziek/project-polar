package com.cryolytix.backend.entities;

import com.cryolytix.backend.dto.Notification;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private boolean read;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    public Notification toDto() {
        Notification notification = new Notification();
        notification.setId(id);
        notification.setDeviceId(device.getId());
        notification.setMessage(message);
        notification.setRead(read);
        notification.setTimestamp(timestamp);
        return notification;
    }
}
