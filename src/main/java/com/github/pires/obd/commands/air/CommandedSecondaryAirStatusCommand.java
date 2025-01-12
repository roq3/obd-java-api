package com.github.pires.obd.commands.air;

import com.github.pires.obd.commands.ObdCommand;

/**
 * Secondary air status.
 *
 * @author roque
 */
public class CommandedSecondaryAirStatusCommand extends ObdCommand {

    private int status = 0;

    public CommandedSecondaryAirStatusCommand() {
        super("01 12");
    }

    @Override
    protected void performCalculations() {
        // The response is a single byte
        status = buffer.get(2);
    }

    @Override
    public String getFormattedResult() {
        return switch (status) {
            case 1 -> "Upstream";
            case 2 -> "Downstream of catalytic converter";
            case 4 -> "From the outside atmosphere or off";
            case 8 -> "Pump commanded on for diagnostics";
            default -> "Unknown";
        };
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(status);
    }

    @Override
    public String getName() {
        return "Commanded Secondary Air Status";
    }
}
