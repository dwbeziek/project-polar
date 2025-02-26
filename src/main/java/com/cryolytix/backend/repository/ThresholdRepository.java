package com.cryolytix.backend.repository;

import com.cryolytix.backend.entity.DeviceEntity;
import com.cryolytix.backend.entity.ThresholdEntity;
import com.cryolytix.backend.enums.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThresholdRepository extends JpaRepository<ThresholdEntity, Long> {

    List<ThresholdEntity> findByDeviceId(Long deviceId);
    Optional<ThresholdEntity> findByDeviceAndSensorType(DeviceEntity device, SensorType sensorType);

}
