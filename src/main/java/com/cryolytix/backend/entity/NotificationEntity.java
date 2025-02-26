package com.cryolytix.backend.entity;

import com.cryolytix.backend.dto.Notification;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "notification_t")
@Data
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean read;

    @Column(nullable = false)
    private LocalDateTime timestamp;

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
