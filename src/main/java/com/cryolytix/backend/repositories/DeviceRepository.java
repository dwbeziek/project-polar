package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

//    Optional<Device> findByLatlng(String latlng);
    Optional<Device> findByImei(String imei);
    Optional<Device> findByCode(String code);

}
