package com.cryolytix.backend.enums;

public enum Unit {
    CELSIUS, PERCENT, VOLTAGE, MOVEMENT_COUNT;

    public static Unit determineUnit(String parameterCode) {
        switch (parameterCode) {
            case "10800": case "10801": return CELSIUS;  // Temperature
            case "10804": case "10805": return PERCENT;  // Humidity
            case "10808": case "10812": return MOVEMENT_COUNT; // Movement
            case "10810": return VOLTAGE; // Battery Voltage
            default: throw new IllegalArgumentException("Unknown unit for parameter code: " + parameterCode);
        }
    }
}
