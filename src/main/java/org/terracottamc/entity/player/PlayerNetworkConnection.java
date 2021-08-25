package org.terracottamc.entity.player;

import io.netty.channel.Channel;
import org.terracottamc.network.packet.Packet;
import org.terracottamc.server.Server;

import java.net.InetSocketAddress;

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
public class PlayerNetworkConnection {

    private final Server server;
    private final Channel rakNetSession;

    /**
     * Creates a new {@link org.terracottamc.entity.player.PlayerNetworkConnection}
     *
     * @param server        which represents the server the {@link org.terracottamc.entity.player.Player} is connected to
     * @param rakNetSession that is the session of the {@link org.terracottamc.entity.player.Player}
     */
    public PlayerNetworkConnection(final Server server, final Channel rakNetSession) {
        this.server = server;
        this.rakNetSession = rakNetSession;
    }

    /**
     * Retrieves the {@link org.terracottamc.server.Server} the {@link org.terracottamc.entity.player.Player}
     * is connected to
     *
     * @return a fresh {@link org.terracottamc.server.Server}
     */
    public Server getServer() {
        return this.server;
    }

    /**
     * Retrieves the {@link io.netty.channel.Channel} which represents the RakNet session
     * of this {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link io.netty.channel.Channel}
     */
    public Channel getRakNetSession() {
        return this.rakNetSession;
    }

    /**
     * Sends a new {@link org.terracottamc.network.packet.Packet} to the client
     * of the {@link org.terracottamc.entity.player.Player} without flushing the data too
     *
     * @param packet the {@link org.terracottamc.network.packet.Packet} which should be sent
     */
    public void sendPacket(final Packet packet) {
        this.sendPacket(packet, false);
    }

    /**
     * Sends a new {@link org.terracottamc.network.packet.Packet} to the client
     * of the {@link org.terracottamc.entity.player.Player}
     *
     * @param packet     the {@link org.terracottamc.network.packet.Packet} which should be sent
     * @param sendDirect whether the data should be flushed too
     */
    public void sendPacket(final Packet packet, final boolean sendDirect) {
        final Player player = this.server.getPlayerByAddress((InetSocketAddress) this.rakNetSession.remoteAddress());

        if (player != null) {
            packet.setProtocolVersion(player.getLoginChainData().getProtocolVersion());
        }

        if (sendDirect) {
            this.rakNetSession.writeAndFlush(packet);
        } else {
            this.rakNetSession.write(packet);
        }
    }
}