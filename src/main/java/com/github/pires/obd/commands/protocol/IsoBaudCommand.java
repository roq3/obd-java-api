package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.enums.ObdIsoBaudRates;

import java.util.Objects;

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

        String rawResult = getResult();

        if (Objects.equals(rawResult, "OK")) {
            rawResult = rate.getName();
        }

        double result = Double.parseDouble(rawResult);

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