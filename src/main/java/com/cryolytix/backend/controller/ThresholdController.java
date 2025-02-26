package com.cryolytix.backend.controller;

import com.cryolytix.backend.dto.Threshold;
import com.cryolytix.backend.services.ThresholdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
@RequiredArgsConstructor
public class ThresholdController {

    private final ThresholdService thresholdService;

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<Threshold>> getThresholds(@PathVariable Long deviceId) {
        return ResponseEntity.ok(thresholdService.getThresholdsForDevice(deviceId));
    }

}
