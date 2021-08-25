package org.terracottamc.network.raknet.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.Packet;
import org.terracottamc.server.Server;
import org.terracottamc.util.BinaryStream;

import java.net.InetSocketAddress;
import java.util.List;

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
public class ProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {

    public static final String NAME = "protocol-decoder";

    private final Server server;

    public ProtocolDecoder() {
        this.server = Server.getInstance();
    }

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf buffer, final List<Object> out) {
        final BinaryStream packetStream = new BinaryStream(buffer);
        final int packetId = packetStream.readUnsignedVarInt() & 0x3FF;
        final Packet readPacket = this.server.getPacketRegistry().retrievePacketById(packetId);

        if (readPacket == null) {
            Server.getInstance().getLogger().warn("Failed to read incoming packet with id: 0x" +
                    Integer.toHexString(packetId));

            return;
        }


        final Player player = this.server.getPlayerByAddress((InetSocketAddress) ctx.channel().remoteAddress());

        if (player != null) {
            readPacket.setProtocolVersion(player.getLoginChainData().getProtocolVersion());
        }

        readPacket.setBuffer(packetStream.getBuffer());
        readPacket.deserialize();

        out.add(readPacket);
    }
}