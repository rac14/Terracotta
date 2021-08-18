package org.terracottamc.network.packet.type;

/**
 * Copyright (c) 2021, TerracottaMC
 * All rights reserved.
 *
 * <p>
 * This project is licensed under the BSD 3-Clause License which
 * can be found in the root directory of this source tree
 *
 * @author Kaooot
 * @version 1.0
 */
public enum ViolationType {

    UNKNOWN,
    MALFORMED_PACKET;

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ViolationType} by their identifier
     *
     * @param violationTypeId which is used to identify
     *                        the {@link org.terracottamc.network.packet.type.ViolationType}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ViolationType}
     */
    public static ViolationType retrieveViolationTypeById(final int violationTypeId) {
        for (final ViolationType violationType : ViolationType.values()) {
            if (violationType.ordinal() == (violationTypeId + 1)) {
                return violationType;
            }
        }

        return null;
    }
}