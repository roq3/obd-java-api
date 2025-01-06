/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.obd.commands.protocol;

import com.github.pires.obd.enums.AvailableCommandNames;

/**
 * Describe the current Protocol.
 * If a protocol is chosen and the automatic option is
 * also selected, AT DP will show the word 'AUTO' before
 * the protocol description. Note that the description
 * shows the actual protocol names, not the numbers
 * used by the protocol setting commands.
 *
 * @since 1.0-RC12
 */
public class DescribeProtocolCommand extends ObdProtocolCommand {

    private String protocolName;

    /**
     * <p>Constructor for DescribeProtocolCommand.</p>
     */
    public DescribeProtocolCommand() {
        super("AT DP");
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return protocolName;
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return protocolName;
    }

    /**
     * <p>performCalculations.</p>
     */
    protected void performCalculations() {
        protocolName = decodeProtocolResponse(getResult());
    }

    public static String decodeProtocolResponse(String rawResponse) {

        if (rawResponse.endsWith(">")) {
            rawResponse = rawResponse.substring(0, rawResponse.length() - 1).trim();
        }

        rawResponse = rawResponse.replaceAll("\\s+", "").trim();

        StringBuilder decodedText = new StringBuilder();
        for (int i = 0; i < rawResponse.length(); i += 2) {
            if (i + 2 <= rawResponse.length()) {
                String hex = rawResponse.substring(i, i + 2);
                try {
                    int charCode = Integer.parseInt(hex, 16);
                    decodedText.append((char) charCode);
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return decodedText.toString().trim();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return AvailableCommandNames.DESCRIBE_PROTOCOL.getValue();
    }
}
