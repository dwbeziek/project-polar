package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

//    Optional<Device> findByLatlng(String latlng);
    Optional<DeviceEntity> findByImei(String imei);
    Optional<DeviceEntity> findById(Long id);
    Optional<DeviceEntity> findByCode(String code);

}
