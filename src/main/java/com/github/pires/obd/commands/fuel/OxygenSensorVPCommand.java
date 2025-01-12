package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.enums.OxygenSensor;

/**
 * Generic command for handling PIDs 14, 15, 16, 17, 18, 19, 1A, 1B.
 * 2 bytes of data
 *
 * @author roque
 */
public class OxygenSensorVPCommand extends OxygenSensorCommand {

    // A: Voltage
    // min value: 0, max value: 1.275, unit: V, formula: A / 200

    // B: Short term fuel trim
    // min value: -100, max value: 99.22, unit: %, formula: (100/128 * B) - 100

    // info: (if B==$FF, sensor is not used in trim calculation)

    // 14 = Oxygen Sensor 1
    // 15 = Oxygen Sensor 2
    // 16 = Oxygen Sensor 3
    // 17 = Oxygen Sensor 4
    // 18 = Oxygen Sensor 5
    // 19 = Oxygen Sensor 6
    // 1A = Oxygen Sensor 7
    // 1B = Oxygen Sensor 8

    private double voltage = 0;
    private double trim = 0;

    public OxygenSensorVPCommand(final OxygenSensor sensor) {
        super(sensor);
    }

    @Override
    protected void performCalculations() {
        // The response is two bytes
        int A = buffer.get(2);
        int B = buffer.get(3);

        // Formula: A / 200
        voltage = A / 200.0;

        // Formula: (100 / 128 * B) - 100
        trim = (100.0 / 128.0 * B) - 100.0;
    }

    @SuppressWarnings("DefaultLocale")
    @Override
    public String getFormattedResult() {
        return String.format("Voltage: %.3f V, Short Term Fuel Trim: %.2f%%", voltage, trim);
    }

    @SuppressWarnings("DefaultLocale")
    @Override
    public String getCalculatedResult() {
        return String.format("%.3f, %.2f", voltage, trim);
    }
}