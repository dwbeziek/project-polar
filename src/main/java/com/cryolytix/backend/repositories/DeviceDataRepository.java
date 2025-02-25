package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceDataEntity, Long> {
    // Latest data for a specific device
    List<DeviceDataEntity> findTop1ByDeviceIdOrderByTimestampDesc(Long deviceId);

    // Historical data for a specific device
    List<DeviceDataEntity> findByDeviceIdAndTimestampAfterOrderByTimestampDesc(Long deviceId, LocalDateTime timestamp);

    // Optional: Latest data for all devices (for optimization, if needed)
    // Note: This isn’t directly supported by JPA without a custom query or aggregation,
    // so we’ll rely on service logic for getAllLatestDeviceData unless you want a custom query.
}
