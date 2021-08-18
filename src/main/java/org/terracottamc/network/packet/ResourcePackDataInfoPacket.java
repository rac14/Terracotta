package org.terracottamc.network.packet;

import org.terracottamc.network.packet.type.ResourcePackDataInfoType;

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
public class ResourcePackDataInfoPacket extends Packet {

    private String resourcePackUuid;
    private int maxChunkSize;
    private int chunkCount;
    private long compressedResourcePackSize;
    private byte[] resourcePackSha256;

    @Override
    public int getPacketId() {
        return Protocol.RESOURCE_PACK_DATA_INFO_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeString(this.resourcePackUuid);
        this.writeIntLE(this.maxChunkSize);
        this.writeIntLE(this.chunkCount);
        this.writeLongLE(this.compressedResourcePackSize);
        this.writeByteArray(this.resourcePackSha256);
        this.writeBoolean(false); // premium
        this.writeByte(ResourcePackDataInfoType.RESOURCE.ordinal());
    }

    /**
     * Sets the resource pack uuid of this {@link org.terracottamc.network.packet.ResourcePackDataInfoPacket}
     *
     * @param resourcePackUuid which represents the updated value
     */
    public void setResourcePackUuid(final String resourcePackUuid) {
        this.resourcePackUuid = resourcePackUuid;
    }

    /**
     * Sets the max chunk size in megabyte of this {@link org.terracottamc.network.packet.ResourcePackDataInfoPacket}
     *
     * @param maxChunkSize which represents the updated value
     */
    public void setMaxChunkSize(final int maxChunkSize) {
        this.maxChunkSize = maxChunkSize;
    }

    /**
     * Sets the chunk count of this {@link org.terracottamc.network.packet.ResourcePackDataInfoPacket}
     *
     * @param chunkCount which represents the updated value
     */
    public void setChunkCount(final int chunkCount) {
        this.chunkCount = chunkCount;
    }

    /**
     * Sets the resource pack size of this {@link org.terracottamc.network.packet.ResourcePackDataInfoPacket}
     *
     * @param compressedResourcePackSize which represents the updated value
     */
    public void setCompressedResourcePackSize(final long compressedResourcePackSize) {
        this.compressedResourcePackSize = compressedResourcePackSize;
    }

    /**
     * Sets the resource pack encryption data
     * of this {@link org.terracottamc.network.packet.ResourcePackDataInfoPacket}
     *
     * @param resourcePackSha256 which represents the updated value
     */
    public void setResourcePackSha256(final byte[] resourcePackSha256) {
        this.resourcePackSha256 = resourcePackSha256;
    }
}