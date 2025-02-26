package com.cryolytix.backend.controller;

import com.cryolytix.backend.model.DeviceDataResponse;
import com.cryolytix.backend.services.DeviceDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


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

    @GetMapping("/{deviceId}/history")
    public DeviceDataResponse getDeviceDataHistory(@PathVariable("deviceId") String deviceId,
                                                   @RequestParam(defaultValue = "1h") String period) {
        try {
            Long id = Long.parseLong(deviceId);
            var history = deviceDataService.getDeviceDataHistory(id, period);
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
