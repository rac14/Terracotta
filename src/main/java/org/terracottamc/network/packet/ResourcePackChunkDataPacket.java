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
public class ResourcePackChunkDataPacket extends Packet {

    private String resourcePackUuid;
    private int chunkIndex;
    private long downloadProgress;
    private byte[] dataToDownload;

    @Override
    public int getPacketId() {
        return Protocol.RESOURCE_PACK_CHUNK_DATA_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeString(this.resourcePackUuid);
        this.writeIntLE(this.chunkIndex);
        this.writeLongLE(this.downloadProgress);
        this.writeByteArray(this.dataToDownload);
    }

    /**
     * Sets the resource pack {@link java.util.UUID}
     *
     * @param resourcePackUuid which is the updated value
     */
    public void setResourcePackUuid(final String resourcePackUuid) {
        this.resourcePackUuid = resourcePackUuid;
    }

    /**
     * Sets the chunk index of this {@link org.terracottamc.network.packet.ResourcePackChunkDataPacket}
     *
     * @param chunkIndex which is the updated value
     */
    public void setChunkIndex(final int chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    /**
     * Sets the download progress of this {@link org.terracottamc.network.packet.ResourcePackChunkDataPacket}
     *
     * @param downloadProgress which is the updated value
     */
    public void setDownloadProgress(final long downloadProgress) {
        this.downloadProgress = downloadProgress;
    }

    /**
     * Sets the data to download of this {@link org.terracottamc.network.packet.ResourcePackChunkDataPacket}
     *
     * @param dataToDownload which is the updated value
     */
    public void setDataToDownload(final byte[] dataToDownload) {
        this.dataToDownload = dataToDownload;
    }
}