package com.cryolytix.backend.model;

import com.cryolytix.backend.dto.DeviceData;
import lombok.Data;

import java.util.List;

@Data
public class DeviceDataResponse {
    private int total;
    private List<DeviceData> results;

    public DeviceDataResponse(List<DeviceData> results) {
        this.total = results.size();
        this.results = results;
    }
}