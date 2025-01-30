package com.cryolytix.backend.enums;

public enum SensorType {
    TEMPERATURE_SENSOR_1("10800", ThresholdType.SENSOR),
    TEMPERATURE_SENSOR_2("10801", ThresholdType.SENSOR),
    HUMIDITY_SENSOR_1("10804", ThresholdType.SENSOR),
    HUMIDITY_SENSOR_2("10805", ThresholdType.SENSOR),
    MOVEMENT_SENSOR("10808", ThresholdType.SENSOR),
    MAGNET_SENSOR("10812", ThresholdType.SENSOR);

    private final String parameterCode;
    private final ThresholdType thresholdType;

    SensorType(String parameterCode, ThresholdType thresholdType) {
        this.parameterCode = parameterCode;
        this.thresholdType = thresholdType;
    }

    public String getParameterCode() {
        return parameterCode;
    }

    public ThresholdType toThresholdType() {
        return thresholdType;
    }

    public static SensorType fromCode(String code) {
        for (SensorType type : values()) {
            if (type.getParameterCode().equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown parameter code: " + code);
    }

    public static boolean isValidCode(String code) {
        for (SensorType type : values()) {
            if (type.getParameterCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
