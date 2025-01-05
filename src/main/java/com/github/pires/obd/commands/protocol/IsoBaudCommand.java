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
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "ISO Baud " + rate.name();
    }

}