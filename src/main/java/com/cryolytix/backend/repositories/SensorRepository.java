package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
