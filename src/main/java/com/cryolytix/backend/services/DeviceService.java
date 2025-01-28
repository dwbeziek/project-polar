package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.events.DeviceAlertEvent;
import com.cryolytix.backend.events.EventPublisher;
import com.cryolytix.backend.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final EventPublisher eventPublisher;

    public DeviceService(DeviceRepository deviceRepository, EventPublisher eventPublisher) {
        this.deviceRepository = deviceRepository;
        this.eventPublisher = eventPublisher;
    }

    public DeviceDTO addDevice(DeviceDTO deviceDTO) {
        Device device = new Device();
        device.setDeviceId(deviceDTO.getDeviceId());
        device.setName(deviceDTO.getName());
        device.setDescription(deviceDTO.getDescription());
        device.setThresholdTemperature(deviceDTO.getThresholdTemperature());

        deviceRepository.save(device);

        // Publish Event (e.g., Device Registered)
        eventPublisher.publishEvent(new DeviceAlertEvent(device.getDeviceId(), device.getThresholdTemperature(), System.currentTimeMillis()));

        return deviceDTO;
    }
}
