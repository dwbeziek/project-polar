package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {

    List<SensorData> findByDeviceDataId(Long deviceDataId);

}
