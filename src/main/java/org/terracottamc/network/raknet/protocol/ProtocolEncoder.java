package org.terracottamc.network.raknet.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
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
public class ProtocolEncoder extends MessageToByteEncoder<Packet> {

    public static final String NAME = "protocol-encoder";

    @Override
    protected void encode(final ChannelHandlerContext ctx, final Packet packet, final ByteBuf out) {
        packet.serialize();

        out.writeBytes(packet.getBuffer());
    }
}