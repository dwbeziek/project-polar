package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.DeviceData;
import com.cryolytix.backend.entities.*;
import com.cryolytix.backend.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceDataService {

    private final DeviceDataRepository deviceDataRepository;
    private final DeviceRepository deviceRepository;
    private final SensorDataRepository sensorDataRepository;
    private final ThresholdRepository thresholdRepository;
    private final NotificationRepository notificationRepository;

    public DeviceDataService(DeviceDataRepository deviceDataRepository, DeviceRepository deviceRepository,
                             SensorDataRepository sensorDataRepository, ThresholdRepository thresholdRepository, NotificationRepository notificationRepository) {
        this.deviceDataRepository = deviceDataRepository;
        this.deviceRepository = deviceRepository;
        this.sensorDataRepository = sensorDataRepository;
        this.thresholdRepository = thresholdRepository;
        this.notificationRepository = notificationRepository;
    }


    public void processDeviceData(DeviceData deviceData) {
        Optional<DeviceEntity> deviceOpt = deviceRepository.findByImei(deviceData.getImei());

        if (deviceOpt.isEmpty()) {
//            TODO log here instead
            System.err.println("⚠ Device not registered: " + deviceData.getImei());
            return;
        }

        DeviceEntity device = deviceOpt.get();
        DeviceDataEntity deviceDataEntity = new DeviceDataEntity();
        deviceDataEntity.setDevice(device);
        deviceDataEntity.setTimestamp(deviceData.getTimestamp());
        deviceDataEntity.setLatitude(deviceData.getLatitude());
        deviceDataEntity.setLongitude(deviceData.getLongitude());
        deviceDataEntity.setAltitude(deviceData.getAltitude());
        deviceDataEntity.setAngle(deviceData.getAngle());
        deviceDataEntity.setSatellites(deviceData.getSatellites());
        deviceDataEntity.setSpeed(deviceData.getSpeed());

        deviceDataEntity = deviceDataRepository.save(deviceDataEntity);

        DeviceDataEntity finalDeviceDataEntity = deviceDataEntity;
        List<SensorDataEntity> sensorDataEntityList = deviceData.getSensorData().stream()
                .map(sensorDTO -> {
                    SensorDataEntity sensorDataEntity = new SensorDataEntity();
                    sensorDataEntity.setDeviceDataEntity(finalDeviceDataEntity);
                    sensorDataEntity.setParameterCode(sensorDTO.getParameterCode());
                    sensorDataEntity.setSensorType(sensorDTO.getSensorType());
                    sensorDataEntity.setValue(sensorDTO.getValue());
                    sensorDataEntity.setUnit(sensorDTO.getUnit());

                    // 🚨 CHECK THRESHOLDS
                    checkThresholdAndTriggerAlert(device, sensorDataEntity);

                    return sensorDataEntity;
                }).toList();

        sensorDataRepository.saveAll(sensorDataEntityList);
    }

    public List<DeviceData> getLatestDeviceData(Long deviceId) {
        List<DeviceDataEntity> deviceData = deviceDataRepository.findTop1ByDeviceIdOrderByTimestampDesc(deviceId);
        return deviceData.stream()
                .map(DeviceDataEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<DeviceData> getDeviceDataHistory(Long deviceId, String period) {
        LocalDateTime startTime;
        int limit;
        switch (period.toLowerCase()) {
            case "1h":
                startTime = LocalDateTime.now().minusHours(1);
                limit = 12; // ~1 point every 5 mins
                break;
            case "24h":
                startTime = LocalDateTime.now().minusHours(24);
                limit = 24; // ~1 point per hour
                break;
            case "1w":
                startTime = LocalDateTime.now().minusWeeks(1);
                limit = 28; // ~4 points per day
                break;
            default:
                startTime = LocalDateTime.now().minusHours(1);
                limit = 12;
        }
       return deviceDataRepository.findByDeviceIdAndTimestampAfterOrderByTimestampDesc(deviceId, startTime)
                .stream()
               .limit(limit)
                .map(DeviceDataEntity::toDto)
                .collect(Collectors.toList());
    }

    public List<DeviceData> getAllLatestDeviceData() {
        List<DeviceEntity> devices = deviceRepository.findAll();
        return devices.stream()
                .map(device -> {
                    List<DeviceDataEntity> latest = deviceDataRepository.findTop1ByDeviceIdOrderByTimestampDesc(device.getId());
                    return latest.isEmpty() ? null : latest.get(0).toDto();
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
    }

    /**
     * Checks if the sensor value is outside the allowed range and triggers an alert.
     */
    private void checkThresholdAndTriggerAlert(DeviceEntity device, SensorDataEntity sensorDataEntity) {
        Optional<ThresholdEntity> thresholdOpt = thresholdRepository.findByDeviceAndSensorType(device, sensorDataEntity.getSensorType());

        if (thresholdOpt.isPresent()) {
            ThresholdEntity thresholdEntity = thresholdOpt.get();

            if ((sensorDataEntity.getValue().compareTo(thresholdEntity.getMinValue()) < 0) || (sensorDataEntity.getValue().compareTo(thresholdEntity.getMaxValue()) > 0)) {
                // 🚨 Sensor is outside threshold → Send Notification
                String alertMessage = "ALERT: " + sensorDataEntity.getSensorType() + " for device " + device.getName() +
                        " is outside the threshold! Current value: " + sensorDataEntity.getValue() +
                        " (Expected: " + thresholdEntity.getMinValue() + " - " + thresholdEntity.getMaxValue() + ")";

                NotificationEntity notification = new NotificationEntity();
                notification.setDevice(device);
                notification.setMessage(alertMessage);
                notification.setRead(false);
                notification.setTimestamp(LocalDateTime.now());
                notificationRepository.save(notification);
                System.out.printf("Notification sent: {%s}", alertMessage);

            }
        }
    }
}
