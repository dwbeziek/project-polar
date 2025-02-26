package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.Device;
import com.cryolytix.backend.entities.DeviceEntity;
import com.cryolytix.backend.exceptions.ResourceNotFoundException;
import com.cryolytix.backend.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService implements PaginatedService<DeviceEntity>{

    private final DeviceRepository deviceRepository;

    @Transactional
    public Device createDevice(Device device) {
        if (getDeviceByImei(device.getImei()).isPresent()) {
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

    @Override
    @Transactional(readOnly = true)
    public Page<DeviceEntity> findAll(Map<String, String> params, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        String name = params.getOrDefault("name", "").trim();
        String imei = params.getOrDefault("imei", "").trim();
        String code = params.getOrDefault("code", "").trim();

        if (name.isEmpty() && imei.isEmpty() && code.isEmpty()) {
            deviceRepository.findAll(pageable).stream().map(DeviceEntity::toDto).collect(toList());
        }
        return deviceRepository.findBySearchParams(name, imei, code, pageable).stream().map(DeviceEntity::toDto).collect(toList());

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
