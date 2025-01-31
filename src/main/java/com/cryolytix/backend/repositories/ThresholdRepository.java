package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.entities.Threshold;
import com.cryolytix.backend.enums.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long> {

    List<Threshold> findByDeviceId(Long deviceId);
    Optional<Threshold> findByDeviceAndSensorType(Device device, SensorType sensorType);

}
