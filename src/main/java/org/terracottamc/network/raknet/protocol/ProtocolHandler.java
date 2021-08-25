package org.terracottamc.network.raknet.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.LoginPacket;
import org.terracottamc.network.packet.Packet;
import org.terracottamc.network.packet.handler.IPacketHandler;
import org.terracottamc.network.packet.handler.LoginPacketHandler;
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
public class ProtocolHandler extends SimpleChannelInboundHandler<Packet> {

    public static final String NAME = "protocol-handler";

    private final Server server;

    private static ChannelPipeline channelPipeline;

    public ProtocolHandler() {
        this.server = Server.getInstance();
    }

    @Override
    public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);

        ProtocolHandler.channelPipeline = ctx.pipeline();
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final Packet packet) {
        final Channel channel = ctx.channel();

        if (packet.getClass().equals(LoginPacket.class)) {
            final LoginPacket loginPacket = (LoginPacket) packet;
            final LoginPacketHandler loginPacketHandler = (LoginPacketHandler)
                    this.server.getPacketRegistry().retrievePacketHandlerByClass(LoginPacket.class);

            loginPacketHandler.handleLogin(loginPacket, channel);

            return;
        }

        final IPacketHandler<Packet> packetHandler = (IPacketHandler<Packet>) this.server.getPacketRegistry()
                .retrievePacketHandlerByClass(packet.getClass());

        if (packetHandler == null) {
            return;
        }

        final Player player = this.server.getPlayerByAddress((InetSocketAddress) channel.remoteAddress());

        if (player != null) {
            packetHandler.handle(packet, player);
        }
    }

    /**
     * Obtains the {@link io.netty.channel.ChannelPipeline}
     * of this {@link org.terracottamc.network.raknet.protocol.ProtocolHandler}
     *
     * @return a fresh {@link io.netty.channel.ChannelPipeline}
     */
    public static ChannelPipeline getChannelPipeline() {
        return ProtocolHandler.channelPipeline;
    }
}