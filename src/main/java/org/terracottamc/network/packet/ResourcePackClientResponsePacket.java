package org.terracottamc.network.packet;

import org.terracottamc.network.packet.type.ResourcePackEntry;
import org.terracottamc.network.packet.type.ResourcePackResponseStatus;

import java.util.ArrayList;
import java.util.List;

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
public class ResourcePackClientResponsePacket extends Packet {

    private final List<ResourcePackEntry> resourcePackEntries = new ArrayList<>();

    private ResourcePackResponseStatus responseStatus;

    @Override
    public int getPacketId() {
        return Protocol.RESOURCE_PACK_CLIENT_RESPONSE_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        final byte responseStatusId = this.readByte();

        this.responseStatus = ResourcePackResponseStatus.retrieveResponseStatusById(responseStatusId);

        final short resourcePackEntriesLength = this.readShortLE();

        for (int i = 0; i < resourcePackEntriesLength; i++) {
            final String[] resourcePackEntryElements = this.readString().split("_");
            final String resourcePackEntryUuid = resourcePackEntryElements[0];
            final String resourcePacketEntryVersion = resourcePackEntryElements[1];

            this.resourcePackEntries.add(new ResourcePackEntry(resourcePackEntryUuid, resourcePacketEntryVersion));
        }
    }

    /**
     * Retrieves the resource pack entries
     * of this {@link org.terracottamc.network.packet.ResourcePackClientResponsePacket}
     *
     * @return a fresh {@link java.util.List}
     */
    public List<ResourcePackEntry> getResourcePackEntries() {
        return this.resourcePackEntries;
    }

    /**
     * Retrieves the received {@link org.terracottamc.network.packet.type.ResourcePackResponseStatus}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ResourcePackResponseStatus}
     */
    public ResourcePackResponseStatus getResponseStatus() {
        return this.responseStatus;
    }
}