package com.cryolytix.backend.controllers;

import com.cryolytix.backend.model.DeviceDataResponse;
import com.cryolytix.backend.services.DeviceDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device-data")
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    public DeviceDataController(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }

    @GetMapping("/{deviceId}/latest")
    public ResponseEntity<DeviceDataResponse> getLatestDeviceData(@PathVariable Long deviceId) {
        var latest = deviceDataService.getLatestDeviceData(deviceId);
        return latest.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new DeviceDataResponse(latest));
    }

    @GetMapping("/{deviceId}")
    public DeviceDataResponse getDeviceDataHistory(@PathVariable Long deviceId) {
        var history = deviceDataService.getDeviceDataHistory(deviceId);
        return new DeviceDataResponse(history);
    }

    @GetMapping("/live")
    public DeviceDataResponse getAllLatestDeviceData() {
        var liveData = deviceDataService.getAllLatestDeviceData();
        return new DeviceDataResponse(liveData);
    }

}
