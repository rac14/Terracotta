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
public class SetTimePacket extends Packet {

    private int time;

    @Override
    public int getPacketId() {
        return Protocol.SET_TIME_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeVarInt(this.time);
    }

    /**
     * Updates the time of this {@link org.terracottamc.network.packet.SetTimePacket}
     *
     * @param time which represents the updated time
     */
    public void setTime(final int time) {
        this.time = time;
    }
}