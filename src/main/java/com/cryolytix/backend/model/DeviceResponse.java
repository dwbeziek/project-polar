package com.cryolytix.backend.model;

import com.cryolytix.backend.dto.Device;
import lombok.Data;

import java.util.List;

@Data
public class DeviceResponse {

    private int total;
    private List<Device> devices;

    public DeviceResponse(List<Device> devices) {
        this.total = devices.size();
        this.devices = devices;
    }
}
