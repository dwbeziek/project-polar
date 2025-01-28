package com.cryolytix.backend.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceAlertEvent {

    private String deviceId;
    private double temperature;
    private long timestamp;
}
