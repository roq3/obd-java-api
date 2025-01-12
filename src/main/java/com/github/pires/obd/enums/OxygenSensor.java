package com.github.pires.obd.enums;

import java.util.HashMap;
import java.util.Map;

public enum OxygenSensor {

    OXYGEN_SENSOR_1(0x34, "Oxygen Sensor 1"), // pid 34
    OXYGEN_SENSOR_2(0x35, "Oxygen Sensor 2"), // pid 35
    OXYGEN_SENSOR_3(0x36, "Oxygen Sensor 3"), // pid 36
    OXYGEN_SENSOR_4(0x37, "Oxygen Sensor 4"), // pid 37
    OXYGEN_SENSOR_5(0x38, "Oxygen Sensor 5"), // pid 38
    OXYGEN_SENSOR_6(0x39, "Oxygen Sensor 6"), // pid 39
    OXYGEN_SENSOR_7(0x3A, "Oxygen Sensor 7"), // pid 3A
    OXYGEN_SENSOR_8(0x3B, "Oxygen Sensor 8"); // pid 3B

    /**
     * Constant <code>map</code>
     */
    private static final Map<Integer, OxygenSensor> map = new HashMap<>();

    static {
        for (OxygenSensor error : OxygenSensor.values())
            map.put(error.getValue(), error);
    }

    private final int value;
    private final String sensor;

    OxygenSensor(final int value, final String sensor) {
        this.value = value;
        this.sensor = sensor;
    }

    public static OxygenSensor fromValue(final int value) {
        return map.get(value);
    }

    public int getValue() {
        return value;
    }

    public String getSensor() {
        return sensor;
    }

    public final String buildObdCommand() {
        return String.format("01 %02X", value);
    }
}