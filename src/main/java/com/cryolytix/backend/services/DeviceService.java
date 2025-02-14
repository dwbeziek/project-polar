package com.cryolytix.backend.services;

import aj.org.objectweb.asm.commons.Remapper;
import com.cryolytix.backend.dto.Device;
import com.cryolytix.backend.entities.DeviceEntity;
import com.cryolytix.backend.exceptions.ResourceNotFoundException;
import com.cryolytix.backend.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public Device createDevice(Device device) {
        if (deviceRepository.findByImei(device.getImei()).isPresent()) {
            throw new IllegalArgumentException("Device with IMEI already exists.");
        }

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setImei(device.getImei());
        deviceEntity.setCode(device.getCode());
        deviceEntity.setName(device.getName());
        deviceEntity.setDescription(device.getDescription());

        return deviceRepository.save(deviceEntity).toDto() ;
    }

    public Device updateDevice(Long id, Device device) {

        Optional<DeviceEntity> deviceOptional = deviceRepository.findById(id);
        if (!deviceOptional.isPresent()) {
            throw new ResourceNotFoundException("Device with id does not exist.");
        }
        DeviceEntity deviceEntity = deviceOptional.get();
        deviceEntity.setImei(device.getImei());
        deviceEntity.setCode(device.getCode());
        deviceEntity.setName(device.getName());
        deviceEntity.setDescription(device.getDescription());
        return deviceRepository.save(deviceEntity).toDto();
    }

    public Optional<Device> findByImei(String imei) {
        return deviceRepository.findByImei(imei).map(DeviceEntity::toDto);
    }

    public Optional<Device> findById(Long id) {
        return deviceRepository.findById(id).map(DeviceEntity::toDto);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll().stream().map(DeviceEntity::toDto).collect(Collectors.toList());
    }

    public void deleteDevice(Long id) {
        Optional<DeviceEntity> deviceOptional = deviceRepository.findById(id);
        if (!deviceOptional.isPresent()) {
            throw new ResourceNotFoundException("Device with id does not exist.");
        }
        deviceRepository.deleteById(id);
    }

//    public List<Device> search(String code, String name, String description) {
//        deviceRepository.searchAllBy
//        return null;
//    }
}
