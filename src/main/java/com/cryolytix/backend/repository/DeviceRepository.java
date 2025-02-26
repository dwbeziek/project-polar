package com.cryolytix.backend.repository;

import com.cryolytix.backend.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    List<DeviceEntity> findByNameContainingIgnoreCase(String search);

    Optional<DeviceEntity> findByImei(String imei);

    List<DeviceEntity> findByNameContainingIgnoreCaseOrImeiContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String imei, String code);
}
