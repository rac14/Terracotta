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
public class TickSyncPacket extends Packet {

    private long requestTimestamp;
    private long responseTimestamp;

    @Override
    public int getPacketId() {
        return Protocol.TICK_SYNC_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        this.requestTimestamp = this.readLongLE();
        this.responseTimestamp = this.readLongLE();
    }

    /**
     * Retrieves the request timestamp of this {@link org.terracottamc.network.packet.TickSyncPacket}
     *
     * @return a fresh request timestamp
     */
    public long getRequestTimestamp() {
        return this.requestTimestamp;
    }

    /**
     * Retrieves the response timestamp of this {@link org.terracottamc.network.packet.TickSyncPacket}
     *
     * @return a fresh response timestamp
     */
    public long getResponseTimestamp() {
        return this.responseTimestamp;
    }
}