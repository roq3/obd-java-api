package com.github.pires.obd.commands.fuel;

import com.github.pires.obd.commands.ObdCommand;

import java.util.Arrays;

public class FuelSystemStatusCommand extends ObdCommand {

    private String statusBank1;
    private String statusBank2;

    public FuelSystemStatusCommand() {
        super("01 03");
    }

    @Override
    protected void performCalculations() {
        getFuelSystemStatus(getResult());
    }

    @Override
    public String getFormattedResult() {
        return "Fuel System Status - Bank 1: " + getStatusDescription(statusBank1) +
                ", Bank 2: " + getStatusDescription(statusBank2);
    }

    @Override
    public String getCalculatedResult() {
        return Arrays.toString(new String[]{statusBank1, statusBank2});
    }

    @Override
    public String getName() {
        return "Fuel System Status";
    }

    private void getFuelSystemStatus(String rawValue) {
        String cleanedResponse = rawValue.replace(" ", "");

        statusBank1 = "";
        statusBank2 = "";

        if (cleanedResponse.length() >= 8) {
            try {
                statusBank1 = cleanedResponse.substring(4, 6);
                statusBank2 = cleanedResponse.substring(6, 8);
            } catch (Exception ignored) {

            }
        }
    }

    private String getStatusDescription(String status) {

        if (status.equals("NODATA") || status.isEmpty()) {
            return "Unknown status";
        }

        return switch (Integer.parseInt(status, 16)) {
            case 0 -> "The motor is off";
            case 1 -> "Open loop due to insufficient engine temperature";
            case 2 -> "Closed loop, using oxygen sensor feedback to determine fuel mix";
            case 4 -> "Open loop due to engine load OR fuel cut due to deceleration";
            case 8 -> "Open loop due to system failure";
            case 16 -> "Closed loop, using at least one oxygen sensor but there is a fault in the feedback system";
            default -> "Unknown status";
        };
    }
}
