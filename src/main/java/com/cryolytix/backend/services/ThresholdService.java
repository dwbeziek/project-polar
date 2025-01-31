package com.cryolytix.backend.services;

import com.cryolytix.backend.dto.ThresholdDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.entities.Threshold;
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
    public void saveThreshold(ThresholdDTO thresholdDTO) {
        Optional<Device> deviceOpt = deviceRepository.findById(Long.parseLong(thresholdDTO.getDeviceId()));

        if (deviceOpt.isPresent()) {
            Device device = deviceOpt.get();

            Threshold threshold = new Threshold();
            threshold.setDevice(device);
            threshold.setThresholdType(thresholdDTO.getThresholdType());
            threshold.setUnit(thresholdDTO.getUnit());
            threshold.setMinValue(thresholdDTO.getMinValue());
            threshold.setMaxValue(thresholdDTO.getMaxValue());

            thresholdRepository.save(threshold);
            log.info("✅ Threshold saved: {}", threshold);
        } else {
            log.warn("❌ Device not found for Threshold Data: {}", thresholdDTO);
        }
    }

    public List<ThresholdDTO> getThresholdsForDevice(Long deviceId) {
        return thresholdRepository.findByDeviceId(deviceId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ThresholdDTO convertToDTO(Threshold threshold) {
        ThresholdDTO dto = new ThresholdDTO();
        dto.setDeviceId(threshold.getDevice().getId().toString());
        dto.setThresholdType(threshold.getThresholdType());
        dto.setUnit(threshold.getUnit());
        dto.setMinValue(threshold.getMinValue());
        dto.setMaxValue(threshold.getMaxValue());
        return dto;
    }
}
