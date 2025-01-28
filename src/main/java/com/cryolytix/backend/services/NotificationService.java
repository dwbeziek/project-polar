package com.cryolytix.backend.services;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendAlert(String deviceId, double temperature) {
        // Logic to send notification (e.g., email, SMS)
        System.out.printf("Alert: Device %s exceeded threshold with temperature %.2f%n", deviceId, temperature);
    }

}
