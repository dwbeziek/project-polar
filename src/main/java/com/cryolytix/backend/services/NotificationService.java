package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.Notification;
import com.cryolytix.backend.entity.NotificationEntity;
import com.cryolytix.backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendAlert(String deviceId, double temperature) {
        // Logic to send notification (e.g., email, SMS)
//        System.out.printf("Alert: Device %s exceeded threshold with temperature %.2f%n", deviceId, temperature);
//        log.debug("✅ Threshold saved: {}", thresholdEntity);
    }


    @Transactional(readOnly = true)
    public List<Notification> getNotificationsByDevice(Long deviceId) {
        return notificationRepository.findByDeviceId(deviceId).stream().map(NotificationEntity::toDto).collect(Collectors.toList());
    }

}
