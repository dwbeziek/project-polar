package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.SensorDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensorDataRepository extends JpaRepository<SensorDataEntity, Long> {

//    List<SensorDataEntity> findByDeviceDataId(Long deviceDataId);

}
