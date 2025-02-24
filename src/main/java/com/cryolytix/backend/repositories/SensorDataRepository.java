package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.SensorDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorDataEntity, Long> {

//    List<SensorDataEntity> findByDeviceDataId(Long deviceDataId);

}
