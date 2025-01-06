package com.github.pires.obd.enums;

/**
 * All OBD ISO Baud rates.
 *
 * @author roque
 */
public enum ObdIsoBaudRates {

    /**
     * rate 10400
     */
    RATE_10(10, "10400"),

    /**
     * rate 4800
     */
    RATE_48(48, "4800"),

    /**
     * rate 9600
     */
    RATE_96(96, "9600");

    private final int value;

    private final String name;

    ObdIsoBaudRates(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
