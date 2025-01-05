package com.github.pires.obd.commands.protocol;

/**
 * Set all to defaults.
 *
 * @author roque
 */
public class SetDefaultsCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for SetDefaultsCommand.</p>
     */
    public SetDefaultsCommand() {
        super("AT D");
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Set Defaults";
    }
}