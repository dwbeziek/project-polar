package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.DeviceDataDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.entities.DeviceData;
import com.cryolytix.backend.entities.SensorData;
import com.cryolytix.backend.entities.Threshold;
import com.cryolytix.backend.repositories.DeviceDataRepository;
import com.cryolytix.backend.repositories.DeviceRepository;
import com.cryolytix.backend.repositories.SensorDataRepository;
import com.cryolytix.backend.repositories.ThresholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceDataService {

    private final DeviceDataRepository deviceDataRepository;
    private final DeviceRepository deviceRepository;
    private final SensorDataRepository sensorDataRepository;
    private final ThresholdRepository thresholdRepository;

    public DeviceDataService(DeviceDataRepository deviceDataRepository, DeviceRepository deviceRepository, SensorDataRepository sensorDataRepository, ThresholdRepository thresholdRepository) {
        this.deviceDataRepository = deviceDataRepository;
        this.deviceRepository = deviceRepository;
        this.sensorDataRepository = sensorDataRepository;
        this.thresholdRepository = thresholdRepository;
    }


    public void processDeviceData(DeviceDataDTO deviceDataDTO) {
        Optional<Device> deviceOpt = deviceRepository.findByImei(deviceDataDTO.getImei());

        if (deviceOpt.isEmpty()) {
//            TODO log here instead
            System.err.println("⚠ Device not registered: " + deviceDataDTO.getImei());
            return;
        }

        Device device = deviceOpt.get();
        DeviceData deviceData = new DeviceData();
        deviceData.setDevice(device);
        deviceData.setTimestamp(deviceDataDTO.getTimestamp());
        deviceData.setLatitude(deviceDataDTO.getLatitude());
        deviceData.setLongitude(deviceDataDTO.getLongitude());
        deviceData.setAltitude(deviceDataDTO.getAltitude());
        deviceData.setAngle(deviceDataDTO.getAngle());
        deviceData.setSatellites(deviceDataDTO.getSatellites());
        deviceData.setSpeed(deviceDataDTO.getSpeed());

        deviceData = deviceDataRepository.save(deviceData);

        DeviceData finalDeviceData = deviceData;
        List<SensorData> sensorDataList = deviceDataDTO.getSensorData().stream()
                .map(sensorDTO -> {
                    SensorData sensorData = new SensorData();
                    sensorData.setDeviceData(finalDeviceData);
                    sensorData.setParameterCode(sensorDTO.getParameterCode());
                    sensorData.setSensorType(sensorDTO.getSensorType());
                    sensorData.setValue(sensorDTO.getValue());
                    sensorData.setUnit(sensorDTO.getUnit());

                    // 🚨 CHECK THRESHOLDS
                    checkThresholdAndTriggerAlert(device, sensorData);

                    return sensorData;
                }).toList();

        sensorDataRepository.saveAll(sensorDataList);
    }

    /**
     * Checks if the sensor value is outside the allowed range and triggers an alert.
     */
    private void checkThresholdAndTriggerAlert(Device device, SensorData sensorData) {
        Optional<Threshold> thresholdOpt = thresholdRepository.findByDeviceAndSensorType(device, sensorData.getSensorType());

        if (thresholdOpt.isPresent()) {
            Threshold threshold = thresholdOpt.get();

            if ((sensorData.getValue().compareTo(threshold.getMinValue()) < 0) || (sensorData.getValue().compareTo(threshold.getMaxValue()) > 0)) {
                // 🚨 Sensor is outside threshold → Send Notification
                String alertMessage = "ALERT: " + sensorData.getSensorType() + " for device " + device.getName() +
                        " is outside the threshold! Current value: " + sensorData.getValue() +
                        " (Expected: " + threshold.getMinValue() + " - " + threshold.getMaxValue() + ")";

//                notificationService.sendAlert(alertMessage, device);
            }
        }
    }

}
