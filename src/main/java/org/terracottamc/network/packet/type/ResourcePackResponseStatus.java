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
public enum ResourcePackResponseStatus {

    REFUSED,
    SEND_PACKS,
    HAVE_ALL_PACKS,
    COMPLETED;

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ResourcePackResponseStatus} by their identifier
     *
     * @param responseStatusId which is used to retrieve
     *                         the {@link org.terracottamc.network.packet.type.ResourcePackResponseStatus}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ResourcePackResponseStatus}
     */
    public static ResourcePackResponseStatus retrieveResponseStatusById(final int responseStatusId) {
        for (final ResourcePackResponseStatus resourcePackResponseStatus : ResourcePackResponseStatus.values()) {
            if (resourcePackResponseStatus.ordinal() == (responseStatusId - 1)) {
                return resourcePackResponseStatus;
            }
        }

        return null;
    }
}