package org.terracottamc.network.packet.handler;

import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.Packet;

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
public interface IPacketHandler<T extends Packet> {

    /**
     * Executes packet handling of the given packet for the given {@link org.terracottamc.entity.player.Player}
     *
     * @param packet which should be handled
     * @param player who is affected in this packet handling
     */
    void handle(final T packet, final Player player);
}