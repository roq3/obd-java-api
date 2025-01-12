package com.github.pires.obd.enums;

import java.util.HashMap;
import java.util.Map;

public enum OxygenSensor {

    OXYGEN_SENSOR_14(0x14, "Oxygen Sensor 1 (14)"), // pid 14
    OXYGEN_SENSOR_15(0x15, "Oxygen Sensor 2 (15)"), // pid 15
    OXYGEN_SENSOR_16(0x16, "Oxygen Sensor 3 (16)"), // pid 16
    OXYGEN_SENSOR_17(0x17, "Oxygen Sensor 4 (17)"), // pid 17
    OXYGEN_SENSOR_18(0x18, "Oxygen Sensor 5 (18)"), // pid 18
    OXYGEN_SENSOR_19(0x19, "Oxygen Sensor 6 (19)"), // pid 19
    OXYGEN_SENSOR_1A(0x1A, "Oxygen Sensor 7 (1A)"), // pid 1A
    OXYGEN_SENSOR_1B(0x1B, "Oxygen Sensor 8 (1B)"), // pid 1B
    OXYGEN_SENSOR_24(0x24, "Oxygen Sensor 1 (24)"), // pid 24
    OXYGEN_SENSOR_25(0x25, "Oxygen Sensor 2 (25)"), // pid 25
    OXYGEN_SENSOR_26(0x26, "Oxygen Sensor 3 (26)"), // pid 26
    OXYGEN_SENSOR_27(0x27, "Oxygen Sensor 4 (27)"), // pid 27
    OXYGEN_SENSOR_28(0x28, "Oxygen Sensor 5 (28)"), // pid 28
    OXYGEN_SENSOR_29(0x29, "Oxygen Sensor 6 (29)"), // pid 29
    OXYGEN_SENSOR_2A(0x2A, "Oxygen Sensor 7 (2A)"), // pid 2A
    OXYGEN_SENSOR_2B(0x2B, "Oxygen Sensor 8 (2B)"), // pid 2B
    OXYGEN_SENSOR_34(0x34, "Oxygen Sensor 1 (34)"), // pid 34
    OXYGEN_SENSOR_35(0x35, "Oxygen Sensor 2 (35)"), // pid 35
    OXYGEN_SENSOR_36(0x36, "Oxygen Sensor 3 (36)"), // pid 36
    OXYGEN_SENSOR_37(0x37, "Oxygen Sensor 4 (37)"), // pid 37
    OXYGEN_SENSOR_38(0x38, "Oxygen Sensor 5 (38)"), // pid 38
    OXYGEN_SENSOR_39(0x39, "Oxygen Sensor 6 (39)"), // pid 39
    OXYGEN_SENSOR_3A(0x3A, "Oxygen Sensor 7 (3A)"), // pid 3A
    OXYGEN_SENSOR_3B(0x3B, "Oxygen Sensor 8 (3B)"); // pid 3B

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