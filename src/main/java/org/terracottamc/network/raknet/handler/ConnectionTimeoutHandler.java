package org.terracottamc.network.raknet.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.terracottamc.entity.player.Player;
import org.terracottamc.server.Server;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

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
public class ConnectionTimeoutHandler extends IdleStateHandler {

    private boolean closed = false;

    public static final String NAME = "connection-timeout-handler";

    public ConnectionTimeoutHandler(final int timeoutSeconds, final TimeUnit timeUnit) {
        super(timeoutSeconds, 0L, 0L, timeUnit);
    }

    @Override
    protected void channelIdle(final ChannelHandlerContext ctx, final IdleStateEvent event) {
        if (!this.closed) {
            final InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            final Player player = Server.getInstance().getPlayerByAddress(address);

            if (player != null) {
                Server.getInstance().getLogger().info("The player " + player.getName() + " disconnected with " +
                        "reason: timeout");
            } else {
                Server.getInstance().getLogger().info("The player " + address.getHostName() + ":" + address.getPort() +
                        " disconnected with reason: timeout");
            }

            this.closed = true;
        }
    }
}