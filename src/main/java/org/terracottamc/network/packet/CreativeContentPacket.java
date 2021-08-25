package org.terracottamc.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import org.terracottamc.taglib.NBTBuilder;
import org.terracottamc.taglib.nbt.io.NBTReader;
import org.terracottamc.taglib.nbt.io.NBTWriter;
import org.terracottamc.util.BedrockResourceDataReader;
import org.terracottamc.util.BinaryStream;

import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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
public class CreativeContentPacket extends Packet {

    @Override
    public int getPacketId() {
        return Protocol.CREATIVE_CONTENT_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        final List<Map<String, Object>> creativeItems = BedrockResourceDataReader
                .retrieveCreativeItemsByProtocolVersion(this.protocolVersion);

        if (creativeItems == null) {
            return;
        }

        this.writeUnsignedVarInt(creativeItems.size());

        int creativeItemAmount = 0;

        final Map<String, Integer> itemNameRuntimeIds = BedrockResourceDataReader
                .retrieveItemNameRuntimeIdsByProtocolVersion(this.protocolVersion);

        if (itemNameRuntimeIds == null) {
            return;
        }

        for (final Map<String, Object> creativeItem : creativeItems) {
            final int itemRuntimeId = itemNameRuntimeIds.get(creativeItem.get("id"));
            final int itemData = (int) (double) creativeItem.getOrDefault("damage", 0D);
            final int blockRuntimeId = (int) (double) creativeItem.getOrDefault("blockRuntimeId", 0D);

            this.writeUnsignedVarInt(creativeItemAmount++);

            byte[] itemNBTData = new byte[0];

            if (creativeItem.containsKey("nbt_b64")) {
                itemNBTData = Base64.getMimeDecoder().decode(((String) creativeItem.get("nbt_b64"))
                        .getBytes(StandardCharsets.UTF_8));
            }

            if (itemRuntimeId == 0) {
                this.writeByte(0);
            } else {
                final int itemAmount = 1;

                this.writeVarInt(itemRuntimeId);
                this.writeShortLE(itemAmount);
                this.writeUnsignedVarInt(itemData);
                this.writeVarInt(blockRuntimeId);

                final ByteBuf userData = ByteBufAllocator.DEFAULT.ioBuffer();
                final BinaryStream userDataStream = new BinaryStream(userData);

                try (final ByteBufOutputStream byteBufOutputStream = new ByteBufOutputStream(userData)) {
                    if (itemNBTData.length > 0) {
                        byteBufOutputStream.writeShort(-1);
                        byteBufOutputStream.writeByte(1);

                        final NBTWriter nbtWriter = new NBTBuilder()
                                .withIOBuffer(userData)
                                .withByteOrder(ByteOrder.LITTLE_ENDIAN)
                                .buildWriter();

                        final ByteBuf buffer = Unpooled.buffer();
                        buffer.writeBytes(itemNBTData);

                        final NBTReader nbtReader = new NBTBuilder()
                                .withIOBuffer(buffer)
                                .withByteOrder(ByteOrder.LITTLE_ENDIAN)
                                .buildReader();

                        nbtWriter.writeTagCompound(nbtReader.createCompound());
                    } else {
                        byteBufOutputStream.writeShort(0);
                    }

                    // can place on string tag size
                    userDataStream.writeIntLE(0);

                    // can destroy string tag size
                    userDataStream.writeIntLE(0);

                    // shield item runtime id
                    if (itemRuntimeId == 355) {
                        userDataStream.writeLongLE(0);
                    }

                    this.writeUnsignedVarInt(userData.readableBytes());
                    this.writeBytes(userData);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}