package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.Device;
import com.cryolytix.backend.entity.DeviceEntity;
import com.cryolytix.backend.exceptions.ResourceNotFoundException;
import com.cryolytix.backend.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Transactional
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

    @Transactional
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

    @Transactional
    public void deleteDevice(Long id) {
        Optional<DeviceEntity> deviceOptional = deviceRepository.findById(id);
        if (!deviceOptional.isPresent()) {
            throw new ResourceNotFoundException("Device with id does not exist.");
        }
        deviceRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Device> getDevices(String name, String imei, String code) {
        if (ObjectUtils.isEmpty(name) && ObjectUtils.isEmpty(imei) && ObjectUtils.isEmpty(code)) {
            return deviceRepository.findAll().stream().map(DeviceEntity::toDto).collect(Collectors.toList());
        }
        return deviceRepository.findByNameOrImeiOrCode(name, imei, code).stream().map(DeviceEntity::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Device> getDevice(Long id) {
        return deviceRepository.findById(id).map(DeviceEntity::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<Device> getDeviceByImei(String imei) {
        return deviceRepository.findByImei(imei).map(DeviceEntity::toDto);
    }

}
