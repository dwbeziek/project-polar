package com.cryolytix.backend.parsers;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.dto.SensorDTO;
import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.Unit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonParser {

    private final ObjectMapper objectMapper;

    public DeviceDTO parseDeviceData(String json) throws Exception {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode reportedNode = rootNode.path("state").path("reported");

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceId(reportedNode.path("deviceId").asText());
        deviceDTO.setTimestamp(reportedNode.path("ts").asLong());
        deviceDTO.setPr(reportedNode.path("pr").asInt());
        deviceDTO.setLatlng(reportedNode.path("latlng").asText());
        deviceDTO.setAltitude(reportedNode.path("alt").asInt());
        deviceDTO.setAngle(reportedNode.path("ang").asInt());
        deviceDTO.setSatellites(reportedNode.path("sat").asInt());
        deviceDTO.setSpeed(reportedNode.path("sp").asInt());
        deviceDTO.setEventCode(reportedNode.path("evt").asInt());

        // ✅ Extract Sensor Data
        List<SensorDTO> sensors = extractSensors(reportedNode, deviceDTO.getDeviceId());
        deviceDTO.setSensors(sensors);

        return deviceDTO;
    }

    public List<SensorDTO> parseSensorData(String json, String deviceId) throws Exception {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode reportedNode = rootNode.path("state").path("reported");

        return extractSensors(reportedNode, deviceId);
    }

    private List<SensorDTO> extractSensors(JsonNode reportedNode, String deviceId) {
        List<SensorDTO> sensors = new ArrayList<>();
        Iterator<String> fieldNames = reportedNode.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();

            if (SensorType.isValidCode(fieldName)) {
                SensorDTO sensorDTO = new SensorDTO();
                sensorDTO.setDeviceId(deviceId);
                sensorDTO.setParameterCode(fieldName);
                sensorDTO.setSensorType(SensorType.fromCode(fieldName));
                sensorDTO.setUnit(Unit.determineUnit(fieldName));

                // ✅ Generic Reflection to Assign Value or Null
                BigDecimal sensorValue = reportedNode.has(fieldName)
                        ? BigDecimal.valueOf(reportedNode.path(fieldName).asDouble())
                        : null;
                sensorDTO.setValue(sensorValue);

                // ✅ Set Additional Fields Dynamically
                autoAssignFields(sensorDTO, reportedNode);

                sensors.add(sensorDTO);
            }
        }
        return sensors;
    }

    private void autoAssignFields(SensorDTO sensorDTO, JsonNode reportedNode) {
        Field[] fields = SensorDTO.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            // Check if the field is in JSON
            if (reportedNode.has(field.getName())) {
                try {
                    if (field.getType().equals(BigDecimal.class)) {
                        field.set(sensorDTO, BigDecimal.valueOf(reportedNode.path(field.getName()).asDouble()));
                    } else if (field.getType().equals(String.class)) {
                        field.set(sensorDTO, reportedNode.path(field.getName()).asText());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error setting field: " + field.getName(), e);
                }
            }
        }
    }
}
