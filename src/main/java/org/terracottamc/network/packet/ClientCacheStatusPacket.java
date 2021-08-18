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
public class ClientCacheStatusPacket extends Packet {

    private boolean supported;

    @Override
    public int getPacketId() {
        return Protocol.CLIENT_CACHE_STATUS_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        this.supported = this.readBoolean();
    }

    /**
     * Retrieves whether the client cache status is supported
     *
     * @return whether the status is supported
     */
    public boolean isSupported() {
        return this.supported;
    }
}