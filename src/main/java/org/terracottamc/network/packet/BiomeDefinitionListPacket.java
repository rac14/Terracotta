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
public class BiomeDefinitionListPacket extends Packet {

    private byte[] biomeDefintionListData;

    @Override
    public int getPacketId() {
        return Protocol.BIOME_DEFINITION_LIST_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeBytes(this.biomeDefintionListData);
    }

    /**
     * Sets the biome definition list data of this {@link org.terracottamc.network.packet.BiomeDefinitionListPacket}
     *
     * @param biomeDefintionListData that should be set
     */
    public void setBiomeDefintionListData(final byte[] biomeDefintionListData) {
        this.biomeDefintionListData = biomeDefintionListData;
    }
}