package com.cryolytix.backend.listeners;

import com.cryolytix.backend.events.DeviceAlertEvent;
import com.cryolytix.backend.services.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DeviceEventListener {

    private final NotificationService notificationService;

    public DeviceEventListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @EventListener
    public void handleDeviceAlertEvent(DeviceAlertEvent event) {
        notificationService.sendAlert(event.getDeviceId(), event.getTemperature());
    }
}
