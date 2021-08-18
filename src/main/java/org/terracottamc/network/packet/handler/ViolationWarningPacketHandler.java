package org.terracottamc.network.packet.handler;

import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.ViolationWarningPacket;
import org.terracottamc.server.Server;

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
public class ViolationWarningPacketHandler implements IPacketHandler<ViolationWarningPacket> {

    @Override
    public void handle(final ViolationWarningPacket packet, final Player player) {
        Server.getInstance().getLogger().warn("A ViolationWarningPacket was received: " + packet);
    }
}