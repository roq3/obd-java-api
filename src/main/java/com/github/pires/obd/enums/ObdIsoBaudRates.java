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
    RATE_10(10),

    /**
     * rate 10400
     */
    RATE_48(48),

    /**
     * rate 10400
     */
    RATE_96(96);

    private final int value;

    ObdIsoBaudRates(int value) {
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a char.
     */
    public int getValue() {
        return value;
    }
}
