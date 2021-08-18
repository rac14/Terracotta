package org.terracottamc.network.packet;

import org.terracottamc.network.packet.type.PlayStatus;

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
public class PlayStatusPacket extends Packet {

    private PlayStatus playStatus;

    @Override
    public int getPacketId() {
        return Protocol.PLAY_STATUS_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeInt(this.playStatus.ordinal());
    }

    /**
     * Updates this {@link org.terracottamc.network.packet.type.PlayStatus}
     *
     * @param playStatus which represents the updated {@link org.terracottamc.network.packet.type.PlayStatus}
     */
    public void setPlayStatus(final PlayStatus playStatus) {
        this.playStatus = playStatus;
    }
}