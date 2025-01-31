package com.cryolytix.backend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {

//    private final DeviceDataRepository deviceDataRepository;
//    private final DeviceRepository deviceRepository;
//    private final ThresholdRepository thresholdRepository;
//
//    @Transactional
//    public void processIncomingData(SensorDataDTO sensorDataDTO) {
//        try {
//            Optional<Device> deviceOpt = deviceRepository.findById(Long.parseLong(sensorDataDTO.getDeviceId()));
//
//            if (deviceOpt.isPresent()) {
//                Device device = deviceOpt.get();
//
//                Sensor sensor = new Sensor();
//                sensor.setDevice(device);
//                sensor.setSensorType(sensorDataDTO.getSensorType());
//                sensor.setUnit(sensorDataDTO.getUnit());
//                sensor.setValue(sensorDataDTO.getValue());
//
//                deviceDataRepository.save(sensor);
//                log.info("✅ Sensor data processed and saved: {}", sensor);
//
//                // 🔥 Check if value exceeds thresholds
//                checkThresholds(device.getId(), sensorDataDTO);
//            } else {
//                log.warn("❌ Device not found for Sensor Data: {}", sensorDataDTO);
//            }
//        } catch (Exception e) {
//            log.error("❌ Error processing sensor data: {}", e.getMessage());
//        }
//    }
//
//    private void checkThresholds(Long deviceId, SensorDataDTO sensorDataDTO) {
//        List<Threshold> thresholds = thresholdRepository.findByThresholdTypeAndParameterCodeAndDeviceId(
//                sensorDataDTO.getSensorType().toThresholdType(),
//                sensorDataDTO.getParameterCode(),
//                deviceId
//        );
//
//        for (Threshold threshold : thresholds) {
//            if (sensorDataDTO.getValue().compareTo(threshold.getMinValue()) < 0 ||
//                    sensorDataDTO.getValue().compareTo(threshold.getMaxValue()) > 0) {
//
//                log.warn("🚨 ALERT: Sensor {} exceeded threshold on Device {} | Value: {} | Range: {} - {} {}",
//                        sensorDataDTO.getSensorType(), deviceId, sensorDataDTO.getValue(), threshold.getMinValue(), threshold.getMaxValue(), sensorDataDTO.getUnit());
//
//                // TODO: Implement notification service to send alerts
//            }
//        }
//    }
}