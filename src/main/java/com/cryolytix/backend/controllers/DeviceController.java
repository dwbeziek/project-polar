package com.cryolytix.backend.controllers;

import com.cryolytix.backend.dto.Device;
import com.cryolytix.backend.model.DeviceResponse;
import com.cryolytix.backend.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("")
    public DeviceResponse getDevices(@RequestParam(required = false) String search) {
        List<Device> devices = deviceService.getDevices(search);
        return new DeviceResponse(devices);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable("id") String id) {
        try {
            Long deviceId = Long.parseLong(id);
            return deviceService.getDevice(deviceId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/byImei/{imei}")
//    public ResponseEntity<Device> getDeviceByImei(@PathVariable String imei) {
//        return deviceService.getDeviceByImei(imei)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }


    @PostMapping("")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.createDevice(device));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("id") String id, @RequestBody Device device) {
        try {
            Long deviceId = Long.parseLong(id);
            device.setId(deviceId);
            return ResponseEntity.ok(deviceService.updateDevice(deviceId, device));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") String id) {
        try {
            Long deviceId = Long.parseLong(id);
            deviceService.deleteDevice(deviceId);
            return ResponseEntity.noContent().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
