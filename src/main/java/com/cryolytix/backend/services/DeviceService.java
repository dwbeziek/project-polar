package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Device registerDevice(DeviceDTO deviceDTO) {
        if (deviceRepository.findByImei(deviceDTO.getImei()).isPresent()) {
            throw new IllegalArgumentException("Device with IMEI already exists.");
        }

        Device device = new Device();
        device.setImei(deviceDTO.getImei());
        device.setCode(deviceDTO.getCode());
        device.setName(deviceDTO.getName());
        device.setDescription(deviceDTO.getDescription());

        return deviceRepository.save(device);
    }

    public Optional<Device> findByImei(String imei) {
        return deviceRepository.findByImei(imei);
    }

}
