package com.cryolytix.backend.config;

import com.cryolytix.backend.listeners.MqttListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MqttConfig {

    private static final String BROKER_URL = "tcp://localhost:1883"; // Local Mosquitto Broker
    private static final String CLIENT_ID = "CryolytixApp";
    private static final String SENSOR_TOPIC = "sensor/data";

    private final MqttListener mqttListener;

    @Bean
    public MqttClient mqttClient() throws Exception {
//        String brokerUrl = "ssl://mqtt.cardashcam.co.za:8883";
//        String clientId = "CryolytixApp";
//
//        MqttClient client = new MqttClient(brokerUrl, clientId);
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setCleanSession(true);
//
//        // Use key-based authentication instead of username and password
//        String serverCertificate = "/home/darrol/devtools/mqtt-dev-keys/mosquitto.pem.crt";
//        String clientCertificate = "/home/darrol/devtools/mqtt-dev-keys/mosquitto.pem";
//        String clientKey = "/home/darrol/devtools/mqtt-dev-keys/mosquitto.pem.key";
//        options.setSocketFactory(SslUtil.getSocketFactory(serverCertificate, clientCertificate, clientKey));
//
//        client.connect(options);
//        return client;

        //local mosquitto broker for now
        MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(10); // 10 sec timeout
        options.setKeepAliveInterval(30); // Keep-alive interval

        // Connect to the broker
        client.connect(options);
        log.info("✅ Connected to MQTT Broker: {}", BROKER_URL);

        // Set MQTT callback listener
        client.setCallback(mqttListener);

        // Subscribe to topic & process data
        client.subscribe(SENSOR_TOPIC);
        log.info("📡 Subscribed to MQTT topic: {}", SENSOR_TOPIC);

        return client;
    }

}
