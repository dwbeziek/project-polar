-- Insert Thresholds Using ENUMs
INSERT INTO thresholdEntity (device_id, sensor_type, threshold_type, min_value, max_value, unit) VALUES
-- Brackenfell Fridge
(1, 'TEMPERATURE', 'RANGE', -5.0, 5.0, 'CELSIUS'),
(1, 'HUMIDITY', 'RANGE', 30.0, 70.0, 'PERCENT'),
(1, 'BATTERY_VOLTAGE', 'LOW', 10.5, 12.6, 'VOLTAGE'),
(1, 'MOVEMENT_COUNT', 'HIGH', 100, 500, 'MOVEMENT_COUNT'),

-- Constantia Freezer
(2, 'TEMPERATURE', 'RANGE', -20.0, -10.0, 'CELSIUS'),
(2, 'HUMIDITY', 'RANGE', 25.0, 60.0, 'PERCENT'),
(2, 'BATTERY_VOLTAGE', 'LOW', 10.5, 12.6, 'VOLTAGE'),
(2, 'MOVEMENT_COUNT', 'HIGH', 100, 500, 'MOVEMENT_COUNT'),

-- Blaauwberg Chiller
(3, 'TEMPERATURE', 'RANGE', 2.0, 8.0, 'CELSIUS'),
(3, 'HUMIDITY', 'RANGE', 35.0, 75.0, 'PERCENT'),
(3, 'BATTERY_VOLTAGE', 'LOW', 10.5, 12.6, 'VOLTAGE'),
(3, 'MOVEMENT_COUNT', 'HIGH', 100, 500, 'MOVEMENT_COUNT'),

-- Ship Cargo Fridge 1
(4, 'TEMPERATURE', 'RANGE', -18.0, -12.0, 'CELSIUS'),
(4, 'HUMIDITY', 'RANGE', 20.0, 50.0, 'PERCENT'),
(4, 'BATTERY_VOLTAGE', 'LOW', 10.5, 12.6, 'VOLTAGE'),
(4, 'MOVEMENT_COUNT', 'HIGH', 100, 500, 'MOVEMENT_COUNT'),

-- Ship Cargo Fridge 2
(5, 'TEMPERATURE', 'RANGE', -18.0, -12.0, 'CELSIUS'),
(5, 'HUMIDITY', 'RANGE', 20.0, 50.0, 'PERCENT'),
(5, 'BATTERY_VOLTAGE', 'LOW', 10.5, 12.6, 'VOLTAGE'),
(5, 'MOVEMENT_COUNT', 'HIGH', 100, 500, 'MOVEMENT_COUNT'),

-- Ship Cargo Fridge 3
(6, 'TEMPERATURE', 'RANGE', -18.0, -12.0, 'CELSIUS'),
(6, 'HUMIDITY', 'RANGE', 20.0, 50.0, 'PERCENT'),
(6, 'BATTERY_VOLTAGE', 'LOW', 10.5, 12.6, 'VOLTAGE'),
(6, 'MOVEMENT_COUNT', 'HIGH', 100, 500, 'MOVEMENT_COUNT');