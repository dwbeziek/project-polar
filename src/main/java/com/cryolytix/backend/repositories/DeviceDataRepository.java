package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDataRepository extends JpaRepository<DeviceDataEntity, Long> {
}
