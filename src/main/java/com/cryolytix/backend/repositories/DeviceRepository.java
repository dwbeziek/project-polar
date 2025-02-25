package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    Optional<DeviceEntity> findByImei(String imei);

    @Query("SELECT d FROM DeviceEntity d WHERE UPPER(d.name) LIKE UPPER(CONCAT('%', :search, '%'))")
    List<DeviceEntity> findByNameContainingIgnoreCase(String search);


}
