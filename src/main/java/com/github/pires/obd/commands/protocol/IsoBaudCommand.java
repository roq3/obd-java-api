package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.enums.ObdIsoBaudRates;

/**
 * Set ISO Baud
 *
 * @author roque
 */
public class IsoBaudCommand extends ObdProtocolCommand {

    private final ObdIsoBaudRates rate;

    /**
     * <p>Constructor for IsoBaudCommand.</p>
     *
     * @param rate a {@link com.github.pires.obd.enums.ObdIsoBaudRates} object.
     */
    public IsoBaudCommand(final ObdIsoBaudRates rate) {
        super("AT IB " + rate.getValue());
        this.rate = rate;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("DefaultLocale")
    @Override
    public String getFormattedResult() {

        double result = Double.parseDouble(getResult());

        return String.format("%.1f %s", result / 1000.0, getResultUnit());
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "ISO Baud " + rate.name();
    }

    public String getResultUnit() {
        return "kbaud";
    }
}