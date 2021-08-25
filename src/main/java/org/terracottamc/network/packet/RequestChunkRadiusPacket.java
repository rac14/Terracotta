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
public class RequestChunkRadiusPacket extends Packet {

    private int chunkRadius;

    @Override
    public int getPacketId() {
        return Protocol.REQUEST_CHUNK_RADIUS_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        this.chunkRadius = this.readVarInt();
    }

    /**
     * Retrieves the chunk radius of this {@link org.terracottamc.network.packet.RequestChunkRadiusPacket}
     *
     * @return a fresh chunk radius
     */
    public int getChunkRadius() {
        return this.chunkRadius;
    }
}