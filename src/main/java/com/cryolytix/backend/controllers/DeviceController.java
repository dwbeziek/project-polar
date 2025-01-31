package com.cryolytix.backend.controllers;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.entities.Device;
import com.cryolytix.backend.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<Device> registerDevice(@RequestBody DeviceDTO deviceDTO) {
        return ResponseEntity.ok(deviceService.registerDevice(deviceDTO));
    }

    @GetMapping("/{imei}")
    public ResponseEntity<Device> getDeviceByImei(@PathVariable String imei) {
        return deviceService.findByImei(imei)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
