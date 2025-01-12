package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.OxygenSensor;

public abstract class OxygenSensorCommand extends ObdCommand {

    protected OxygenSensor sensor;

    public OxygenSensorCommand(final OxygenSensor sensor) {
        super(sensor.buildObdCommand());
        this.sensor = sensor;
    }

    public final String getSensor() {
        return sensor.getSensor();
    }

    @Override
    public String getName() {
        return sensor.getSensor();
    }
}
