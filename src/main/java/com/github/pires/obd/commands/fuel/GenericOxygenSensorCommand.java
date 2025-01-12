package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.OxygenSensor;

/**
 * Generic command for handling PIDs 34, 35, 36, 37, 38, 39, 3A, 3B.
 *
 * @author roque
 */
public class GenericOxygenSensorCommand extends ObdCommand {

    // AB: Air-Fuel Equivalence Ratio
    // min value: 0, max value: <2, unit: ratio, formula: 2 / 65536 * (256 * A + B)

    // CD: Current
    // min value: -128, max value: <128 unit: mA, formula: (256 * C + D) / 256 - 128

    // 34 = Oxygen Sensor 1
    // 35 = Oxygen Sensor 2
    // 36 = Oxygen Sensor 3
    // 37 = Oxygen Sensor 4
    // 38 = Oxygen Sensor 5
    // 39 = Oxygen Sensor 6
    // 3A = Oxygen Sensor 7
    // 3B = Oxygen Sensor 8

    private final OxygenSensor sensor;
    private double ratio = 0;
    private double current = 0;

    public GenericOxygenSensorCommand(final OxygenSensor sensor) {
        super(sensor.buildObdCommand());
        this.sensor = sensor;
    }

    public final String getSensor() {
        return sensor.getSensor();
    }

    @Override
    protected void performCalculations() {
        // The response is four bytes
        int A = buffer.get(2);
        int B = buffer.get(3);
        int C = buffer.get(4);
        int D = buffer.get(5);

        // Formula: 2 / 65536 * (256 * A + B)
        ratio = 2.0 / 65536.0 * (256 * A + B);

        // Formula: (256 * C + D) / 256 - 128
        current = (256 * C + D) / 256.0 - 128;
    }

    @SuppressWarnings("DefaultLocale")
    @Override
    public String getFormattedResult() {
        return String.format("Air-Fuel Equivalence Ratio: %.6f, Current: %.2f mA", ratio, current);
    }

    @SuppressWarnings("DefaultLocale")
    @Override
    public String getCalculatedResult() {
        return String.format("%.6f, %.2f mA", ratio, current);
    }

    @Override
    public String getName() {
        return sensor.getSensor();
    }
}