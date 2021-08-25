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
public class ChunkRadiusUpdatedPacket extends Packet {

    private int updatedChunkRadius;

    @Override
    public int getPacketId() {
        return Protocol.CHUNK_RADIUS_UPDATED_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeVarInt(this.updatedChunkRadius);
    }

    /**
     * Sets the updated chunk radius
     *
     * @param updatedChunkRadius which should be set
     */
    public void setUpdatedChunkRadius(final int updatedChunkRadius) {
        this.updatedChunkRadius = updatedChunkRadius;
    }
}