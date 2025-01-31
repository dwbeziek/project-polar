package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {
}
