package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.Threshold;
import com.cryolytix.backend.entities.DeviceEntity;
import com.cryolytix.backend.entities.ThresholdEntity;
import com.cryolytix.backend.repositories.DeviceRepository;
import com.cryolytix.backend.repositories.ThresholdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThresholdService {

    private final ThresholdRepository thresholdRepository;
    private final DeviceRepository deviceRepository;

    @Transactional
    public void saveThreshold(Threshold threshold) {
        Optional<DeviceEntity> deviceOpt = deviceRepository.findById(threshold.getDeviceId());

        if (deviceOpt.isPresent()) {
            DeviceEntity device = deviceOpt.get();

            ThresholdEntity thresholdEntity = new ThresholdEntity();
            thresholdEntity.setDevice(device);
            thresholdEntity.setThresholdType(threshold.getThresholdType());
            thresholdEntity.setUnit(threshold.getUnit());
            thresholdEntity.setMinValue(threshold.getMinValue());
            thresholdEntity.setMaxValue(threshold.getMaxValue());

            thresholdRepository.save(thresholdEntity);
            log.debug("✅ Threshold saved: {}", thresholdEntity);
        } else {
            log.warn("❌ Device not found for Threshold Data: {}", threshold);
        }
    }

    public List<Threshold> getThresholdsForDevice(Long deviceId) {
        return thresholdRepository.findByDeviceId(deviceId)
                .stream()
                .map(ThresholdEntity::toDto)
                .collect(Collectors.toList());
    }

}
