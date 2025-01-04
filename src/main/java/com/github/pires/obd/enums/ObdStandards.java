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
package com.github.pires.obd.enums;

/**
 * All OBD standards.
 *
 */
public enum ObdStandards {

    OBD_II_CARB(1, "OBD-II as defined by the CARB"),
    OBD_EPA(2, "OBD as defined by the EPA"),
    OBD_OBD_II(3, "OBD and OBD-II"),
    OBD_I(4, "OBD-I"),
    NOT_OBD_COMPLIANT(5, "Not OBD compliant"),
    EOBD_EUROPE(6, "EOBD (Europe)"),
    EOBD_OBD_II(7, "EOBD and OBD-II"),
    EOBD_OBD(8, "EOBD and OBD"),
    EOBD_OBD_OBD_II(9, "EOBD, OBD and OBD-II"),
    JOBD_JAPAN(10, "JOBD (Japan)"),
    JOBD_OBD_II(11, "JOBD and OBD-II"),
    JOBD_EOBD(12, "JOBD and EOBD"),
    JOBD_EOBD_OBD_II(13, "JOBD, EOBD, and OBD-II"),
    RESERVED_14(14, "Reserved"),
    RESERVED_15(15, "Reserved"),
    RESERVED_16(16, "Reserved"),
    EMD(17, "Engine Manufacturer Diagnostics (EMD)"),
    EMD_PLUS(18, "Engine Manufacturer Diagnostics Enhanced (EMD+)"),
    HD_OBD_C(19, "Heavy Duty On-Board Diagnostics (Child/Partial) (HD OBD-C)"),
    HD_OBD(20, "Heavy Duty On-Board Diagnostics (HD OBD)"),
    WWH_OBD(21, "World Wide Harmonized OBD (WWH OBD)"),
    RESERVED_22(22, "Reserved"),
    HD_EOBD_I(23, "Heavy Duty Euro OBD Stage I without NOx control (HD EOBD-I)"),
    HD_EOBD_I_N(24, "Heavy Duty Euro OBD Stage I with NOx control (HD EOBD-I N)"),
    HD_EOBD_II(25, "Heavy Duty Euro OBD Stage II without NOx control (HD EOBD-II)"),
    HD_EOBD_II_N(26, "Heavy Duty Euro OBD Stage II with NOx control (HD EOBD-II N)"),
    RESERVED_27(27, "Reserved"),
    OBDBR_1(28, "Brazil OBD Phase 1 (OBDBr-1)"),
    OBDBR_2(29, "Brazil OBD Phase 2 (OBDBr-2)"),
    KOBD(30, "Korean OBD (KOBD)"),
    IOBD_I(31, "India OBD I (IOBD I)"),
    IOBD_II(32, "India OBD II (IOBD II)"),
    HD_EOBD_IV(33, "Heavy Duty Euro OBD Stage VI (HD EOBD-IV)"),
    RESERVED_34_250(34, "Reserved [34-250]"),
    RESERVED_251_255(251, "Not available for assignment (SAE J1939 special meaning) [251-255]"),

    UNKNOWN(0, "Unknown");

    private final int value;
    private final String description;

    ObdStandards(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static ObdStandards fromValue(int value) {

        if (value >= 251 && value <= 255) {
            return RESERVED_251_255;
        }

        if (value >= 34 && value <= 250) {
            return RESERVED_34_250;
        }

        for (ObdStandards standard : values()) {
            if (standard.value == value) {
                return standard;
            }
        }
        return null; // or throw an IllegalArgumentException
    }
}
