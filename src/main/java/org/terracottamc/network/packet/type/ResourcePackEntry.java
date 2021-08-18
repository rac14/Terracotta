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
public class ResourcePackEntry {

    private final String uuid;
    private final String version;

    /**
     * Creates a new {@link org.terracottamc.network.packet.type.ResourcePackEntry} with given uniqueId and version
     *
     * @param uuid    that is the uniqueId of this {@link org.terracottamc.network.packet.type.ResourcePackEntry}
     * @param version that is the version of this {@link org.terracottamc.network.packet.type.ResourcePackEntry}
     */
    public ResourcePackEntry(final String uuid, final String version) {
        this.uuid = uuid;
        this.version = version;
    }

    /**
     * Retrieves the {@link java.util.UUID} of this {@link org.terracottamc.network.packet.type.ResourcePackEntry}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Returns the version of this {@link org.terracottamc.network.packet.type.ResourcePackEntry}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getVersion() {
        return this.version;
    }
}