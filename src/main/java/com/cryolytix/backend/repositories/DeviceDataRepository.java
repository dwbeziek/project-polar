package com.cryolytix.backend.repositories;

import com.cryolytix.backend.entities.DeviceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceDataEntity, Long> {

    List<DeviceDataEntity> findTop1ByDeviceIdOrderByTimestampDesc(Long deviceId);
    List<DeviceDataEntity> findByDeviceIdAndTimestampAfterOrderByTimestampDesc(Long deviceId, LocalDateTime timestamp);

}
