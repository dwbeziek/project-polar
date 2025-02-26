package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {

    @Query("SELECT d FROM DeviceEntity d WHERE UPPER(d.name) LIKE UPPER(CONCAT('%', :search, '%'))")
    List<DeviceEntity> findByNameContainingIgnoreCase(String search);

    Optional<DeviceEntity> findByImei(String imei);

    @Query("SELECT d FROM DeviceEntity d WHERE " +
            "(:name IS NULL OR UPPER(d.name) LIKE UPPER(CONCAT('%', :name, '%'))) AND " +
            "(:imei IS NULL OR UPPER(d.imei) LIKE UPPER(CONCAT('%', :imei, '%'))) AND " +
            "(:code IS NULL OR UPPER(d.code) LIKE UPPER(CONCAT('%', :code, '%')))")
    Page<DeviceEntity> findBySearchParams(String name, String imei, String code, Pageable pageable);


}
