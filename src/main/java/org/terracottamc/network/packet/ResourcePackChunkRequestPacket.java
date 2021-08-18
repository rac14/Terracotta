package org.terracottamc.network.packet;

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
public class ResourcePackChunkRequestPacket extends Packet {

    private String resourcePackUuid;
    private int chunkIndex;

    @Override
    public int getPacketId() {
        return Protocol.RESOURCE_PACK_CHUNK_REQUEST_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        this.resourcePackUuid = this.readString();
        this.chunkIndex = this.readIntLE();
    }

    /**
     * Retrieves the uniqueId of the resource pack
     * of this {@link org.terracottamc.network.packet.ResourcePackChunkRequestPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getResourcePackUuid() {
        return this.resourcePackUuid;
    }

    /**
     * Retrieves the chunk index of this {@link org.terracottamc.network.packet.ResourcePackChunkRequestPacket}
     *
     * @return a fresh chunk index
     */
    public int getChunkIndex() {
        return this.chunkIndex;
    }
}