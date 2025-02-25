package com.cryolytix.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Device {

    private Long id;
    private String imei;
    private String code;
    private String name;
    private String description;

}
