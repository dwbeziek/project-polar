package com.cryolytix.backend.listeners;

import com.cryolytix.backend.dto.DeviceDTO;
import com.cryolytix.backend.parsers.JsonParser;
import com.cryolytix.backend.services.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class MqttListener implements MqttCallback {



    private final JsonParser jsonParser;
    private final DeviceService deviceService;


    @Override
    public void connectionLost(Throwable cause) {
        log.error("⚠️ MQTT Connection Lost: {}", cause.getMessage());
        // Reconnect Logic (Optional)
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
        log.info("📩 Received MQTT Message on topic `{}`: {}", topic, payload);

        try {
            if (topic.equalsIgnoreCase("sensor/data")) {
                DeviceDTO deviceDTO = jsonParser.parseDeviceData(payload);

                if (deviceDTO == null) {
                    log.warn("⚠️ Skipping message: Could not parse device data.");
                    return;
                }

                deviceService.processIncomingData(deviceDTO);
                log.info("✅ Device data processed successfully.");
            } else {
                log.warn("⚠️ Unrecognized topic `{}`. Message ignored.", topic);
            }
        } catch (Exception e) {
            log.error("❌ Error processing MQTT message: {}", e.getMessage(), e);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("✅ MQTT Message delivered successfully.");
    }
}
