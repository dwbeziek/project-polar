package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.SensorDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.entities.Sensor;
import com.cryolytix.backend.repositories.DeviceRepository;
import com.cryolytix.backend.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final DeviceRepository deviceRepository;

    public SensorService(SensorRepository sensorRepository, DeviceRepository deviceRepository) {
        this.sensorRepository = sensorRepository;
        this.deviceRepository = deviceRepository;
    }

    public SensorDTO addSensor(SensorDTO sensorDTO) {
        Optional<Device> deviceOpt = deviceRepository.findById(Long.parseLong(sensorDTO.getDeviceId()));
        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();

            Sensor sensor = new Sensor();
            sensor.setSensorType(sensorDTO.getSensorType());
            sensor.setUnit(sensorDTO.getUnit());
            sensor.setValue(sensorDTO.getValue());
            sensor.setDevice(device);

            sensorRepository.save(sensor);

            return sensorDTO;
        } else {
            throw new IllegalArgumentException("Device not found");
        }
    }
}
