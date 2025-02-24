package com.cryolytix.backend.model;

import com.cryolytix.backend.entities.DeviceDataEntity;
import lombok.Data;

import java.util.List;

@Data
public class DeviceDataResponse {
    private int total;
    private List<DeviceDataEntity> results;

    public DeviceDataResponse(List<DeviceDataEntity> results) {
        this.total = results.size();
        this.results = results;
    }
}