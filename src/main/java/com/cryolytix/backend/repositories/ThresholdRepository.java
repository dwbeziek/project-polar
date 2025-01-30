package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.Threshold;
import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.ThresholdType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long> {

    List<Threshold> findByDeviceId(Long deviceId);
    List<Threshold> findByThresholdTypeAndParameterCodeAndDeviceId(ThresholdType thresholdType, String parameterCode, Long deviceId);
}
