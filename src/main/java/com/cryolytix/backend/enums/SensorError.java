package com.cryolytix.backend.enums;

public enum SensorError {

    ABNORMAL_SENSOR_STATE(4000, "Abnormal sensor state"),
    SENSOR_NOT_FOUND(3000, "Sensor not found"),
    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    Temperature_Data _Not_Received_by_FM_tracker(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");
//    FAILED_SENSOR_DATA_PARSING(2000, "Failed sensor data parsing");

    private final int code;
    private final String description;

    SensorError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
