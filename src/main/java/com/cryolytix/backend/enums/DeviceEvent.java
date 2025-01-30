package com.cryolytix.backend.enums;

public enum DeviceEvent {
    MOTION_DETECTED(10829),
    SHOCK_DETECTED(10831),
    BATTERY_LOW(385);

    private final int code;

    DeviceEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DeviceEvent fromCode(int code) {
        for (DeviceEvent event : values()) {
            if (event.getCode() == code) {
                return event;
            }
        }
        throw new IllegalArgumentException("Unknown event code: " + code);
    }
}
