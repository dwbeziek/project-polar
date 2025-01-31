package com.cryolytix.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceDTO {

    private String imei;
    private String code;
    private String name;
    private String description;
    private List<ThresholdDTO> thresholds;
//    private List<NotificationDTO> notifications;

}
