package org.terracottamc.network.packet.handler;

import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.SetLocalPlayerAsInitializedPacket;

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
public class SetLocalPlayerAsInitializedPacketHandler implements IPacketHandler<SetLocalPlayerAsInitializedPacket> {

    @Override
    public void handle(final SetLocalPlayerAsInitializedPacket packet, final Player player) {

    }
}