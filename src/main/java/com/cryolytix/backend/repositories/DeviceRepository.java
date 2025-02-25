package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Query("SELECT d FROM DeviceEntity d LEFT JOIN FETCH d.thresholdEntities LEFT JOIN FETCH d.notifications WHERE UPPER(d.imei) LIKE UPPER(:imei)")
    Optional<DeviceEntity> findByImei(String imei);

    @Query("SELECT d FROM DeviceEntity d LEFT JOIN FETCH d.thresholdEntities LEFT JOIN FETCH d.notifications WHERE UPPER(d.name) LIKE UPPER(CONCAT('%', :search, '%'))")
    List<DeviceEntity> findByNameContainingIgnoreCase(String search);

    @Query("SELECT d FROM DeviceEntity d LEFT JOIN FETCH d.thresholdEntities LEFT JOIN FETCH d.notifications")
    List<DeviceEntity> findAll(); // Override to fetch eagerly

}
