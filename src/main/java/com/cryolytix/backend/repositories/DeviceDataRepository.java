package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceDataEntity, Long> {

    @Query("SELECT d FROM DeviceDataEntity d JOIN FETCH d.device dev LEFT JOIN FETCH dev.thresholdEntities LEFT JOIN FETCH dev.notifications WHERE d.device.id = :deviceId ORDER BY d.timestamp DESC")
    List<DeviceDataEntity> findTop1ByDeviceIdOrderByTimestampDesc(Long deviceId);

    @Query("SELECT d FROM DeviceDataEntity d JOIN FETCH d.device dev LEFT JOIN FETCH dev.thresholdEntities LEFT JOIN FETCH dev.notifications WHERE d.device.id = :deviceId AND d.timestamp > :timestamp ORDER BY d.timestamp DESC")
    List<DeviceDataEntity> findByDeviceIdAndTimestampAfterOrderByTimestampDesc(Long deviceId, LocalDateTime timestamp);
}
