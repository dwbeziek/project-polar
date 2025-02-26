package com.cryolytix.backend.repository;

import com.cryolytix.backend.entity.DeviceDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceDataEntity, Long> {

    List<DeviceDataEntity> findTop1ByDeviceIdOrderByTimestampDesc(Long deviceId);
    List<DeviceDataEntity> findByDeviceIdAndTimestampAfterOrderByTimestampDesc(Long deviceId, LocalDateTime timestamp);

}
