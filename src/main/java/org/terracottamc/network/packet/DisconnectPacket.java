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
public class DisconnectPacket extends Packet {

    private boolean hideDisconnectScreen;
    private String disconnectMessage;

    @Override
    public int getPacketId() {
        return Protocol.DISCONNECT_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeBoolean(this.hideDisconnectScreen);

        if (!this.hideDisconnectScreen) {
            this.writeString(this.disconnectMessage);
        }
    }

    /**
     * Updates whether the disconnect screen should be shown or not
     *
     * @param hideDisconnectScreen which represents the updated value
     */
    public void setHideDisconnectScreen(final boolean hideDisconnectScreen) {
        this.hideDisconnectScreen = hideDisconnectScreen;
    }

    /**
     * Updates the disconnect message
     *
     * @param disconnectMessage which represents the updated value
     */
    public void setDisconnectMessage(final String disconnectMessage) {
        this.disconnectMessage = disconnectMessage;
    }
}