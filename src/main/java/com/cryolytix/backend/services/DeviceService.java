package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.dto.SensorDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.entities.Sensor;
import com.cryolytix.backend.repositories.DeviceRepository;
import com.cryolytix.backend.repositories.SensorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final SensorService sensorService;

    @Transactional
    public void processIncomingData(DeviceDTO deviceDTO) {
        try {
            log.info("📥 Processing device data for timestamp: {}", deviceDTO.getTimestamp());

            // Check if device exists or create new
            Device device = deviceRepository.findByLatlng(deviceDTO.getLatlng())
                    .orElseGet(() -> new Device());

            device.setTimestamp(deviceDTO.getTimestamp());
            device.setPr(deviceDTO.getPr());
            device.setLatlng(deviceDTO.getLatlng());
            device.setAltitude(deviceDTO.getAltitude());
            device.setAngle(deviceDTO.getAngle());
            device.setSatellites(deviceDTO.getSatellites());
            device.setSpeed(deviceDTO.getSpeed());
            device.setEventCode(deviceDTO.getEventCode());

            deviceRepository.save(device);
            log.info("✅ Device data saved: {}", device);

            // Process sensors
            if (deviceDTO.getSensors() != null) {
                for (SensorDTO sensorDTO : deviceDTO.getSensors()) {
                    sensorDTO.setDeviceId(device.getId().toString()); // Ensure device ID is set
                    sensorService.processIncomingData(sensorDTO);
                }
                ;
                log.info("✅ Sensor data saved for device {}", device.getId());
            }

        } catch (Exception e) {
            log.error("❌ Error processing device data: {}", e.getMessage());
        }
    }

}
