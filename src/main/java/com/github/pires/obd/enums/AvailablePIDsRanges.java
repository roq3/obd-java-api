package com.github.pires.obd.enums;

public enum AvailablePIDsRanges {

    PIDS_01_TO_20("00"),

    PIDS_21_TO_40("20"),

    PIDS_41_TO_60("40"),

    PIDS_61_TO_80("60"),

    PIDS_81_TO_A0("80");

    private final String value;

    AvailablePIDsRanges(String value) {
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a char.
     */
    public String getValue() {
        return value;
    }
}
