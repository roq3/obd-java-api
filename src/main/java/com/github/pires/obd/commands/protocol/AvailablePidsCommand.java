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

import com.github.pires.obd.commands.PersistentCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Retrieve available PIDs
 */
public abstract class AvailablePidsCommand extends PersistentCommand {

    private List<String> pids = new ArrayList<>();

    /**
     * Default ctor.
     *
     * @param command a {@link java.lang.String} object.
     */
    public AvailablePidsCommand(String command) {
        super(command);
    }

    /**
     * Copy ctor.
     *
     * @param other a {@link com.github.pires.obd.commands.protocol.AvailablePidsCommand} object.
     */
    public AvailablePidsCommand(AvailablePidsCommand other) {
        super(other);
    }

    /** {@inheritDoc} */
    @Override
    protected void performCalculations() {
        pids = getSupportedPids(getResult());
    }

    /** {@inheritDoc} */
    @Override
    public String getFormattedResult() {
        return pids.toString().replace("[", "").replace("]", "");
    }

    /** {@inheritDoc} */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(pids);
    }

    /**
     * Decode the hexadecimal response and return a list of supported PIDs.
     *
     * @return a list of supported PIDs.
     */
    public List<String> getSupportedPids(String result) {
        return parseCustomPIDs(result);
    }

    private List<String> parseCustomPIDs(String rawValue) {
        List<String> responses = Arrays.stream(rawValue.split("(?<=\\G.{12})"))
                .filter(response -> response.startsWith("41"))
                .collect(Collectors.toList());

        List<String> pidsList = new ArrayList<>();

        for (String response : responses) {
            for (Integer pid : parsePIDs(response)) {
                pidsList.add(String.format("%02X", pid));
            }
        }

        return pidsList;
    }

    // Formatowanie odpowiedzi dla komend 01 00 i 01 20
    private List<Integer> parsePIDs(String rawResponse) {
        String pid = rawResponse.substring(2, 4);
        int initialPID = Integer.parseInt(pid, 16);

        String cleanResponse = rawResponse.replace(" ", "").substring(4);

        StringBuilder binaryData = new StringBuilder();
        for (int i = 0; i < cleanResponse.length(); i += 2) {
            String byteHex = cleanResponse.substring(i, i + 2);
            int byteValue = Integer.parseInt(byteHex, 16);
            binaryData.append(String.format("%8s", Integer.toBinaryString(byteValue)).replace(' ', '0'));
        }

        List<Integer> supportedPids = new ArrayList<>();
        for (int i = 0; i < binaryData.length(); i++) {
            if (binaryData.charAt(i) == '1') {
                supportedPids.add(i + 1 + initialPID);
            }
        }

        return supportedPids;
    }
}
