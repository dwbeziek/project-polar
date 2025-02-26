package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Query("SELECT d FROM DeviceEntity d WHERE UPPER(d.name) LIKE UPPER(CONCAT('%', :search, '%'))")
    List<DeviceEntity> findByNameContainingIgnoreCase(String search);

    Optional<DeviceEntity> findByImei(String imei);

    @Query("SELECT d FROM DeviceEntity d WHERE " +
            "(:name IS NOT NULL AND UPPER(d.name) LIKE UPPER(CONCAT('%', :name, '%'))) OR " +
            "(:imei IS NOT NULL AND UPPER(d.imei) LIKE UPPER(CONCAT('%', :imei, '%'))) OR " +
            "(:code IS NOT NULL AND UPPER(d.code) LIKE UPPER(CONCAT('%', :code, '%')))")
    List<DeviceEntity> findByNameOrImeiOrCode(String name, String imei, String code);
}
