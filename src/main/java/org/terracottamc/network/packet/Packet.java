package org.terracottamc.network.packet;

import org.terracottamc.util.BinaryStream;

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
public abstract class Packet extends BinaryStream {

    protected int protocolVersion;

    /**
     * Defines the identifier of this {@link org.terracottamc.network.packet.Packet}
     *
     * @return a fresh packet id
     */
    public abstract int getPacketId();

    /**
     * This method is used to decode this {@link org.terracottamc.network.packet.Packet}
     */
    public void deserialize() {

    }

    /**
     * This method is used to encode this {@link org.terracottamc.network.packet.Packet}
     */
    public void serialize() {
        this.writeUnsignedVarInt(this.getPacketId());
    }

    /**
     * Sets the protocol version to work with of this {@link org.terracottamc.network.packet.Packet}
     *
     * @param protocolVersion which should be set to this {@link org.terracottamc.network.packet.Packet}
     */
    public void setProtocolVersion(final int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
}