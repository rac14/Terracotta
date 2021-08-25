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
public class AvailableEntityIdentifiersPacket extends Packet {

    private byte[] entityIdentifiersData;

    @Override
    public int getPacketId() {
        return Protocol.AVAILABLE_ENTITY_IDENTIFIERS_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeBytes(this.entityIdentifiersData);
    }

    /**
     * Sets the entity identifiers data of this {@link org.terracottamc.network.packet.AvailableEntityIdentifiersPacket}
     *
     * @param entityIdentifiersData which should be set
     */
    public void setEntityIdentifiersData(final byte[] entityIdentifiersData) {
        this.entityIdentifiersData = entityIdentifiersData;
    }
}