package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceEntity;
import com.cryolytix.backend.entities.ThresholdEntity;
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
