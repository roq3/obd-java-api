package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;

/**
 * Oxygen Sensors Present.
 *
 * @author roque
 */
public class OxygenSensorsPresentCommand extends ObdCommand {

    private int sensors = 0;

    public OxygenSensorsPresentCommand() {
        super("01 13");
    }

    @Override
    protected void performCalculations() {
        // The response is a single byte
        sensors = buffer.get(2);
    }

    @Override
    public String getFormattedResult() {
        return "Oxygen Sensors Present: " + getSensorsDescription(sensors);
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(sensors);
    }

    private String getSensorsDescription(int sensors) {
        StringBuilder descriptions = new StringBuilder();

        if ((sensors & 0x01) != 0) descriptions.append("Bank 1 - Sensor 1, ");
        if ((sensors & 0x02) != 0) descriptions.append("Bank 1 - Sensor 2, ");
        if ((sensors & 0x04) != 0) descriptions.append("Bank 1 - Sensor 3, ");
        if ((sensors & 0x08) != 0) descriptions.append("Bank 1 - Sensor 4, ");
        if ((sensors & 0x10) != 0) descriptions.append("Bank 2 - Sensor 1, ");
        if ((sensors & 0x20) != 0) descriptions.append("Bank 2 - Sensor 2, ");
        if ((sensors & 0x40) != 0) descriptions.append("Bank 2 - Sensor 3, ");
        if ((sensors & 0x80) != 0) descriptions.append("Bank 2 - Sensor 4, ");

        if (descriptions.length() > 0) {
            descriptions.setLength(descriptions.length() - 2); // Remove trailing comma and space
        }
        return descriptions.toString();
    }

    @Override
    public String getName() {
        return "Oxygen Sensors Present";
    }
}
