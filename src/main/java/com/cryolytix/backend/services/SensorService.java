package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.SensorDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.entities.Sensor;
import com.cryolytix.backend.entities.Threshold;
import com.cryolytix.backend.repositories.DeviceRepository;
import com.cryolytix.backend.repositories.SensorRepository;
import com.cryolytix.backend.repositories.ThresholdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final DeviceRepository deviceRepository;
    private final ThresholdRepository thresholdRepository;

    @Transactional
    public void processIncomingData(SensorDTO sensorDTO) {
        try {
            Optional<Device> deviceOpt = deviceRepository.findById(Long.parseLong(sensorDTO.getDeviceId()));

            if (deviceOpt.isPresent()) {
                Device device = deviceOpt.get();

                Sensor sensor = new Sensor();
                sensor.setDevice(device);
                sensor.setSensorType(sensorDTO.getSensorType());
                sensor.setUnit(sensorDTO.getUnit());
                sensor.setValue(sensorDTO.getValue());

                sensorRepository.save(sensor);
                log.info("✅ Sensor data processed and saved: {}", sensor);

                // 🔥 Check if value exceeds thresholds
                checkThresholds(device.getId(), sensorDTO);
            } else {
                log.warn("❌ Device not found for Sensor Data: {}", sensorDTO);
            }
        } catch (Exception e) {
            log.error("❌ Error processing sensor data: {}", e.getMessage());
        }
    }

    private void checkThresholds(Long deviceId, SensorDTO sensorDTO) {
        List<Threshold> thresholds = thresholdRepository.findByThresholdTypeAndParameterCodeAndDeviceId(
                sensorDTO.getSensorType().toThresholdType(),
                sensorDTO.getParameterCode(),
                deviceId
        );

        for (Threshold threshold : thresholds) {
            if (sensorDTO.getValue().compareTo(threshold.getMinValue()) < 0 ||
                    sensorDTO.getValue().compareTo(threshold.getMaxValue()) > 0) {

                log.warn("🚨 ALERT: Sensor {} exceeded threshold on Device {} | Value: {} | Range: {} - {} {}",
                        sensorDTO.getSensorType(), deviceId, sensorDTO.getValue(), threshold.getMinValue(), threshold.getMaxValue(), sensorDTO.getUnit());

                // TODO: Implement notification service to send alerts
            }
        }
    }
}