package com.cryolytix.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {

    private Long id;
    private Long deviceId;
    private String message;
    private boolean read;
    private LocalDateTime timestamp;
}
