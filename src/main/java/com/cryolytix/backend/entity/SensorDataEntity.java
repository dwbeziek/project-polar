package com.cryolytix.backend.entity;

import com.cryolytix.backend.dto.SensorData;
import com.cryolytix.backend.enums.SensorType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "sensor_data_t")
@Data
public class SensorDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_data_id", nullable = false)
    private DeviceDataEntity deviceDataEntity;

    @Column(name = "parameter_code", nullable = false)
    private String parameterCode; // The actual sensor parameter code (e.g., "10800" for temperature)

    @Enumerated(EnumType.STRING)
    @Column(name = "sensor_type", nullable = false)
    private SensorType sensorType; // Temperature, Humidity, Movement, Battery, etc.

    private BigDecimal value; // Sensor value (temperature, humidity, etc.)

    private String unit; // °C, %, V, etc.

    public SensorData toDto() {
        SensorData sensorData = new SensorData();
        sensorData.setId(id);
        sensorData.setParameterCode(parameterCode);
        sensorData.setSensorType(sensorType);
        sensorData.setValue(value);
        sensorData.setUnit(unit);
        return sensorData;
    }


}
