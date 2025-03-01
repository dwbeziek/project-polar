package com.cryolytix.backend.controller;

import com.cryolytix.backend.dto.Notification;
import com.cryolytix.backend.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable("deviceId") Long deviceId) {
        return ResponseEntity.ok(notificationService.getNotificationsByDevice(deviceId));
    }

}
