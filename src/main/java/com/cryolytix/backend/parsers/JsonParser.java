package com.cryolytix.backend.parsers;

import com.cryolytix.backend.dto.DeviceData;
import com.cryolytix.backend.dto.SensorData;
import com.cryolytix.backend.enums.SensorType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonParser {

    private final ObjectMapper objectMapper;

    public DeviceData parseDeviceData(String json) throws Exception {
        JsonNode node = objectMapper.readTree(json);
        JsonNode reported = node.get("state").get("reported");

        if (reported == null) {
            throw new IllegalArgumentException("Invalid JSON structure: 'reported' field missing");
        }

        DeviceData deviceData = new DeviceData();
        deviceData.setImei(reported.has("deviceId") ? reported.get("deviceId").asText() : null);
        deviceData.setTimestamp(
                reported.has("ts") ? LocalDateTime.ofInstant(Instant.ofEpochMilli(reported.get("ts").asLong()), ZoneId.systemDefault()) : null);
        deviceData.setLatitude(reported.has("latlng") ? Double.parseDouble(reported.get("latlng").asText().split(",")[0]) : 0.0);
        deviceData.setLongitude(reported.has("latlng") ? Double.parseDouble(reported.get("latlng").asText().split(",")[1]) : 0.0);
        deviceData.setAltitude(reported.has("alt") ? reported.get("alt").asInt() : 0);
        deviceData.setAngle(reported.has("ang") ? reported.get("ang").asInt() : 0);
        deviceData.setSatellites(reported.has("sat") ? reported.get("sat").asInt() : 0);
        deviceData.setSpeed(reported.has("sp") ? reported.get("sp").asInt() : 0);

        // Dynamically assign available fields to DTO using reflection
        autoAssignFields(deviceData, reported);

        // Extract Sensor Data dynamically
        deviceData.setSensorData(extractSensorData(reported));

        return deviceData;
    }

    private List<SensorData> extractSensorData(JsonNode reported) {
        List<SensorData> sensorDataList = new ArrayList<>();

        // Define sensor parameter codes and their corresponding types and units
        int[] sensorCodes = {10800, 10804, 10808, 10810, 10812, 10814, 10816};
        SensorType[] sensorTypes = {
                SensorType.TEMPERATURE, SensorType.HUMIDITY, SensorType.MOVEMENT_COUNT, SensorType.BATTERY_VOLTAGE,
                SensorType.ROLL, SensorType.PITCH, SensorType.MAGNET_COUNT
        };
        String[] sensorUnits = {"°C", "%", "count", "V", "°", "°", "count"};

        // Iterate over sensors dynamically
        for (int i = 0; i < sensorCodes.length; i++) {
            for (int sensorIndex = 0; sensorIndex < 4; sensorIndex++) {
                int sensorCode = sensorCodes[i] + sensorIndex;
                String sensorCodeStr = String.valueOf(sensorCode);

                if (reported.has(sensorCodeStr)) {
                    sensorDataList.add(new SensorData(
                            sensorCodeStr,
                            sensorTypes[i],
                            new BigDecimal(String.valueOf(reported.get(sensorCodeStr))),
                            sensorUnits[i]
                    ));
                }
            }
        }

        return sensorDataList;
    }

    /**
     * Dynamically maps JSON fields to DeviceDataDTO fields using Reflection.
     */
    private void autoAssignFields(DeviceData dto, JsonNode reported) {
        Field[] fields = DeviceData.class.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            if (reported.has(fieldName)) {
                try {
                    field.setAccessible(true);
                    if (field.getType().equals(String.class)) {
                        field.set(dto, reported.get(fieldName).asText());
                    } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                        field.set(dto, reported.get(fieldName).asInt());
                    } else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
                        field.set(dto, reported.get(fieldName).asDouble());
                    }
                } catch (IllegalAccessException e) {
                    System.err.println("Error assigning field: " + fieldName);
                }
            }
        }
    }
}
