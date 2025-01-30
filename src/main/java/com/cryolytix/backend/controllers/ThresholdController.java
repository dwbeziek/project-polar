package com.cryolytix.backend.controllers;

import com.cryolytix.backend.dto.ThresholdDTO;
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

    @PostMapping
    public ResponseEntity<String> addThreshold(@RequestBody ThresholdDTO thresholdDTO) {
        thresholdService.saveThreshold(thresholdDTO);
        return ResponseEntity.ok("✅ Threshold successfully added");
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<List<ThresholdDTO>> getThresholds(@PathVariable Long deviceId) {
        return ResponseEntity.ok(thresholdService.getThresholdsForDevice(deviceId));
    }
}
