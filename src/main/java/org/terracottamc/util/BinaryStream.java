package org.terracottamc.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.terracottamc.entity.metadata.EntityMetadataFlag;
import org.terracottamc.entity.metadata.EntityMetadataValue;
import org.terracottamc.math.Vector;
import org.terracottamc.world.gamerule.GameRule;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
public class BinaryStream {

    private ByteBuf buffer;

    /**
     * Creates a new {@link org.terracottamc.util.BinaryStream} with buffer which has an initialCapacity of zero
     */
    public BinaryStream() {
        this.buffer = Unpooled.buffer(0);
    }

    /**
     * Creates a new {@link org.terracottamc.util.BinaryStream} with given {@link io.netty.buffer.ByteBuf}
     *
     * @param buffer which is used to create the {@link org.terracottamc.util.BinaryStream}
     */
    public BinaryStream(final ByteBuf buffer) {
        this.buffer = buffer;
        this.buffer.retain();
    }

    /**
     * Updates the used buffer to the given {@link io.netty.buffer.ByteBuf}
     *
     * @param buffer which should be updated
     */
    public void setBuffer(final ByteBuf buffer) {
        this.buffer = buffer;
    }

    /**
     * Retrieves this {@link io.netty.buffer.ByteBuf}
     *
     * @return a fresh {@link io.netty.buffer.ByteBuf}
     */
    public ByteBuf getBuffer() {
        return this.buffer;
    }

    /**
     * Retrieves all available bytes from the buffer
     *
     * @return a fresh amount of available bytes
     */
    public int readAvailableBytes() {
        return this.buffer.readableBytes();
    }

    public void readBytes(final byte[] bytes) {
        this.buffer.readBytes(bytes);
    }

    public ByteBuf readBytes(final int value) {
        return this.buffer.readBytes(value);
    }

    public void writeBytes(final ByteBuf value) {
        this.buffer.writeBytes(value);
    }

    public void writeBytes(final byte[] value) {
        this.buffer.writeBytes(value);
    }

    public void writeByteArray(final byte[] value) {
        this.writeUnsignedVarInt(value.length);
        this.writeBytes(value);
    }

    public byte[] readByteArray() {
        final byte[] data = new byte[this.readUnsignedVarInt()];

        this.readBytes(data);

        return data;
    }

    public byte readByte() {
        return this.buffer.readByte();
    }

    public void writeByte(final int value) {
        this.buffer.writeByte(value);
    }

    public short readShort() {
        return this.buffer.readShort();
    }

    public void writeShort(final int value) {
        this.buffer.writeShort(value);
    }

    public short readShortLE() {
        return this.buffer.readShortLE();
    }

    public void writeShortLE(final int value) {
        this.buffer.writeShortLE(value);
    }

    public int readInt() {
        return this.buffer.readInt();
    }

    public void writeInt(final int value) {
        this.buffer.writeInt(value);
    }

    public int readIntLE() {
        return this.buffer.readIntLE();
    }

    public void writeIntLE(final int value) {
        this.buffer.writeIntLE(value);
    }

    public float readFloat() {
        return this.buffer.readFloat();
    }

    public void writeFloat(final float value) {
        this.buffer.writeFloat(value);
    }

    public float readFloatLE() {
        return this.buffer.readFloatLE();
    }

    public void writeFloatLE(final float value) {
        this.buffer.writeFloatLE(value);
    }

    public double readDouble() {
        return this.buffer.readDouble();
    }

    public void writeDouble(final double value) {
        this.buffer.writeDouble(value);
    }

    public double readDoubleLE() {
        return this.buffer.readDoubleLE();
    }

    public void writeDoubleLE(final double value) {
        this.buffer.writeDoubleLE(value);
    }

    public long readLong() {
        return this.buffer.readLong();
    }

    public void writeLong(final long value) {
        this.buffer.writeLong(value);
    }

    public long readLongLE() {
        return this.buffer.readLongLE();
    }

    public void writeLongLE(final long value) {
        this.buffer.writeLongLE(value);
    }

    public boolean readBoolean() {
        return this.buffer.readBoolean();
    }

    public void writeBoolean(final boolean value) {
        this.buffer.writeBoolean(value);
    }

    public String readString() {
        final int length = this.readUnsignedVarInt();
        final byte[] bytes = new byte[length];

        this.readBytes(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public void writeString(final String value) {
        final byte[] bytes = value.getBytes(StandardCharsets.UTF_8);

        this.writeUnsignedVarInt(bytes.length);
        this.writeBytes(bytes);
    }

    public int readVarInt() {
        return this.deserializeZigZag32(this.readUnsignedVarInt());
    }

    public void writeVarInt(final int value) {
        this.writeUnsignedVarInt(this.serializeZigZag32(value));
    }

    public void writeVarLong(final long value) {
        this.writeVarBigInteger(this.serializeZigZag64(value));
    }

    public UUID readUuid() {
        final long mostSignificantBits = this.readLongLE();
        final long leastSignificantBits = this.readLongLE();

        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    public void writeUuid(final UUID uuid) {
        this.writeLongLE(uuid.getMostSignificantBits());
        this.writeLongLE(uuid.getLeastSignificantBits());
    }

    public short readUnsignedByte() {
        return this.buffer.readUnsignedByte();
    }

    public int readUnsignedShort() {
        return this.buffer.readUnsignedShort();
    }

    public int readUnsignedVarInt() {
        int value = 0;
        int i = 0;
        int b;

        while (((b = this.buffer.readByte()) & 0x80) != 0) {
            value |= (b & 0x7F) << i;
            i += 7;
            if (i > 35) {
                throw new RuntimeException("read UnsignedVarInt is too big");
            }
        }

        return value | (b << i);
    }

    public void writeUnsignedVarInt(int value) {
        while ((value & 0xFFFFFF80) != 0L) {
            this.buffer.writeByte((byte) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }

        this.buffer.writeByte((byte) (value & 0x7F));
    }

    public long readUnsignedVarLong() {
        long value = 0L;
        int i = 0;

        do {
            long b;
            if (((b = this.buffer.readByte()) & 128L) == 0L) {
                return value | b << i;
            }

            value |= (b & 127L) << i;
            i += 7;
        } while (i <= 63);

        throw new RuntimeException("read UnsignedVarLong is too big");
    }

    public void writeUnsignedVarLong(long value) {
        while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
            this.writeByte((byte) (((int) value & 0x7F) | 0x80));
            value >>>= 7;
        }

        this.writeByte((byte) ((int) value & 0x7F));
    }

    public Vector readVector() {
        final float x = this.readFloatLE();
        final float y = this.readFloatLE();
        final float z = this.readFloatLE();

        return new Vector(x, y, z);
    }

    public void writeVector(final Vector vector) {
        this.writeFloatLE(vector.getX());
        this.writeFloatLE(vector.getY());
        this.writeFloatLE(vector.getZ());
    }

    public Vector readBlockVector() {
        final int x = this.readVarInt();
        final int y = this.readUnsignedVarInt();
        final int z = this.readVarInt();

        return new Vector(x, y, z);
    }

    public void writeBlockVector(final Vector vector) {
        this.writeVarInt(vector.getBlockX());
        this.writeUnsignedVarInt(vector.getBlockY());
        this.writeVarInt(vector.getBlockZ());
    }

    public void writeGameRules(final List<GameRule<?>> gameRules) {
        this.writeUnsignedVarInt(gameRules.size());

        for (final GameRule<?> gameRule : gameRules) {
            this.writeString(gameRule.getGameRuleType().name().toLowerCase().replaceAll("_", ""));
            this.writeBoolean(true);

            int type = 1;

            if (gameRule.getDefaultValue().getClass().equals(Integer.class)) {
                type = 2;
            } else if (gameRule.getDefaultValue().getClass().equals(Float.class)) {
                type = 3;
            }

            this.writeUnsignedVarInt(type);

            switch (type) {
                case 1:
                    this.writeBoolean((Boolean) gameRule.getDefaultValue());
                    break;
                case 2:
                    this.writeUnsignedVarInt((Integer) gameRule.getDefaultValue());
                    break;
                case 3:
                    this.writeFloatLE((Float) gameRule.getDefaultValue());
                    break;
            }
        }
    }

    public void writeEntityMetaData(final Map<EntityMetadataFlag, EntityMetadataValue<?>> metadataValues) {
        this.writeUnsignedVarInt(metadataValues.size());

        for (final Map.Entry<EntityMetadataFlag, EntityMetadataValue<?>> metadataEntry : metadataValues.entrySet()) {
            final EntityMetadataFlag entityMetadataFlag = metadataEntry.getKey();
            final EntityMetadataValue<?> entityMetadataValue = metadataEntry.getValue();

            this.writeUnsignedVarInt(entityMetadataFlag.ordinal());
            this.writeUnsignedVarInt(entityMetadataValue.getEntityMetadataFlagType().ordinal());

            switch (entityMetadataValue.getEntityMetadataFlagType()) {
                case BYTE:
                    this.writeByte((byte) entityMetadataValue.getValue());
                    break;
                case SHORT:
                    this.writeShortLE((short) entityMetadataValue.getValue());
                    break;
                case FLOAT:
                    this.writeFloatLE((float) entityMetadataValue.getValue());
                    break;
                case STRING:
                    this.writeString((String) entityMetadataValue.getValue());
                    break;
                case LONG:
                    this.writeVarLong((long) entityMetadataValue.getValue());
                    break;
                default:
                    break;
            }
        }
    }

    private void writeVarBigInteger(BigInteger value) {
        BigInteger unsigned_long_max_value = new BigInteger("FFFFFFFFFFFFFFFF", 16);

        if (value.compareTo(unsigned_long_max_value) > 0) {
            throw new IllegalArgumentException("The value is too big");
        }

        value = value.and(unsigned_long_max_value);

        final BigInteger i = BigInteger.valueOf(0xffffff80);
        final BigInteger x7f = BigInteger.valueOf(0x7f);
        final BigInteger x80 = BigInteger.valueOf(0x80);

        while (!value.and(i).equals(BigInteger.ZERO)) {
            this.writeByte(value.and(x7f).or(x80).byteValue());

            value = value.shiftRight(7);
        }

        this.writeByte(value.byteValue());
    }

    private BigInteger serializeZigZag64(final long value) {
        final BigInteger origin = BigInteger.valueOf(value);
        final BigInteger leftShifted = origin.shiftLeft(1);
        final BigInteger rightShifted = origin.shiftLeft(63);

        return leftShifted.xor(rightShifted);
    }

    private int serializeZigZag32(final int value) {
        return value << 1 ^ value >> 31;
    }

    private int deserializeZigZag32(final int value) {
        return value >> 1 ^ -(value & 1);
    }
}