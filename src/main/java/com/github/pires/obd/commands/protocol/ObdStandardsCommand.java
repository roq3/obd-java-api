package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.ObdStandards;

import java.util.Objects;

/**
 * OBD standards.
 *
 * @author roque
 */
public class ObdStandardsCommand extends ObdCommand {

    private ObdStandards obdStandard = ObdStandards.UNKNOWN;

    public ObdStandardsCommand() {
        super("01 1C");
    }

    /**
     * <p>performCalculations.</p>
     */
    protected void performCalculations() {
        String result = getResult().replaceAll("\\s", "");
        if (result.length() < 6) {
            obdStandard = ObdStandards.UNKNOWN;
            return;
        }
        String hexValue = result.substring(4, 6); // Extract the correct part of the result
        int value = Integer.parseInt(hexValue, 16);
        ObdStandards standard = ObdStandards.fromValue(value);
        this.obdStandard = Objects.requireNonNullElse(standard, ObdStandards.UNKNOWN);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("DefaultLocale")
    @Override
    public String getFormattedResult() {
        return obdStandard.getDescription();
    }

    @Override
    public String getCalculatedResult() {
        return obdStandard.name();
    }

    @Override
    public String getName() {
        return "OBD Standards";
    }

    public ObdStandards getObdStandards() {
        return obdStandard;
    }
}