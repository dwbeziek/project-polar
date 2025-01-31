-- Create ENUM types only if they don't already exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'unit_enum') THEN
        CREATE TYPE unit_enum AS ENUM ('CELSIUS', 'PERCENT', 'VOLTAGE', 'MOVEMENT_COUNT');
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'sensor_type_enum') THEN
        CREATE TYPE sensor_type_enum AS ENUM ('TEMPERATURE', 'HUMIDITY', 'BATTERY_VOLTAGE');
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'threshold_type_enum') THEN
        CREATE TYPE threshold_type_enum AS ENUM ('HIGH', 'LOW', 'RANGE');
    END IF;
END $$;