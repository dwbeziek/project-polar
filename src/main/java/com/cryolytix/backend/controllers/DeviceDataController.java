package com.cryolytix.backend.controllers;

import com.cryolytix.backend.model.DeviceDataResponse;
import com.cryolytix.backend.services.DeviceDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/device-data")
public class DeviceDataController {

    private final DeviceDataService deviceDataService;

    public DeviceDataController(DeviceDataService deviceDataService) {
        this.deviceDataService = deviceDataService;
    }

    @GetMapping("/{deviceId}/latest")
    public ResponseEntity<DeviceDataResponse> getLatestDeviceData(@PathVariable("deviceId") String deviceId) {
        try {
            Long id = Long.parseLong(deviceId);
            var latest = deviceDataService.getLatestDeviceData(id);
            return latest.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new DeviceDataResponse(latest));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(new DeviceDataResponse(Collections.emptyList()));
        }
    }

    @GetMapping("/{deviceId}")
    public DeviceDataResponse getDeviceDataHistory(@PathVariable("deviceId") String deviceId) {
        try {
            Long id = Long.parseLong(deviceId);
            var history = deviceDataService.getDeviceDataHistory(id);
             return new DeviceDataResponse(history);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(new DeviceDataResponse(Collections.emptyList())).getBody();
        }
    }

    @GetMapping("/live")
    public DeviceDataResponse getAllLatestDeviceData() {
        var liveData = deviceDataService.getAllLatestDeviceData();
        return new DeviceDataResponse(liveData);
    }

}
