package com.cryolytix.backend.parsers;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.dto.SensorDTO;
import com.cryolytix.backend.enums.SensorType;
import com.cryolytix.backend.enums.Unit;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

    private final ObjectMapper objectMapper;

    public JsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public DeviceDTO parseDeviceData(String json) throws Exception {
        return objectMapper.readValue(json, DeviceDTO.class);
    }

    public SensorDTO parseSensorData(String json) throws Exception {
        JsonNode node = objectMapper.readTree(json);
        SensorDTO sensorDTO = new SensorDTO();

        sensorDTO.setSensorType(String.valueOf(SensorType.valueOf(node.get("sensorType").asText().toUpperCase())));
        sensorDTO.setUnit(String.valueOf(Unit.valueOf(node.get("unit").asText().toUpperCase())));
        sensorDTO.setValue(node.get("value").asDouble());
        sensorDTO.setDeviceId(node.get("deviceId").asText());

        return sensorDTO;
    }
}
