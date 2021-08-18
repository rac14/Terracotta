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
public enum ViolationSeverity {

    UNKNOWN,
    WARNING,
    FINAL_WARNING,
    TERMINATING_CONNECTION;

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ViolationSeverity} by their identifier
     *
     * @param violationSeverityId which is used to identify
     *                            the {@link org.terracottamc.network.packet.type.ViolationSeverity}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ViolationSeverity}
     */
    public static ViolationSeverity retrieveViolationSeverityById(final int violationSeverityId) {
        for (final ViolationSeverity violationSeverity : ViolationSeverity.values()) {
            if (violationSeverity.ordinal() == violationSeverityId) {
                return violationSeverity;
            }
        }

        return null;
    }
}