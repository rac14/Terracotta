package org.terracottamc.entity.metadata;

import java.util.HashMap;
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
public class EntityMetadata {

    private final Map<EntityMetadataFlag, EntityMetadataValue<?>> metadataValues = new HashMap<>();

    /**
     * Sets a {@link java.lang.Byte} value to its given {@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag which has a role like a key that holds the value
     * @param value              which should be set
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata setByte(final EntityMetadataFlag entityMetadataFlag, final byte value) {
        this.metadataValues.put(entityMetadataFlag, new EntityMetadataValue<Byte>(EntityMetadataFlagType.BYTE, value));

        return this;
    }

    /**
     * Sets a {@link java.lang.Short} value to its given {@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag which has a role like a key that holds the value
     * @param value              which should be set
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata setShort(final EntityMetadataFlag entityMetadataFlag, final short value) {
        this.metadataValues.put(entityMetadataFlag, new EntityMetadataValue<Short>(EntityMetadataFlagType.SHORT, value));

        return this;
    }

    /**
     * Sets a {@link java.lang.Float} value to its given {@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag which has a role like a key that holds the value
     * @param value              which should be set
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata setFloat(final EntityMetadataFlag entityMetadataFlag, final float value) {
        this.metadataValues.put(entityMetadataFlag, new EntityMetadataValue<Float>(EntityMetadataFlagType.FLOAT, value));

        return this;
    }

    /**
     * Sets a {@link java.lang.String} value to its given {@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag which has a role like a key that holds the value
     * @param value              which should be set
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata setString(final EntityMetadataFlag entityMetadataFlag, final String value) {
        this.metadataValues.put(entityMetadataFlag, new EntityMetadataValue<String>(EntityMetadataFlagType.STRING, value));

        return this;
    }

    /**
     * Sets a {@link java.lang.Long} value to its given {@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag which has a role like a key that holds the value
     * @param value              which should be set
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata setLong(final EntityMetadataFlag entityMetadataFlag, final long value) {
        this.metadataValues.put(entityMetadataFlag, new EntityMetadataValue<Long>(EntityMetadataFlagType.LONG, value));

        return this;
    }

    /**
     * Sets a data flag value with given {@link org.terracottamc.entity.metadata.EntityMetadataFlag} flags
     *
     * @param firstFlag  that mostly stands for the flag index
     * @param secondFlag which is the flag to edit that functions as a key
     * @param value      which should be set
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata setDataFlag(final EntityMetadataFlag firstFlag, final EntityFlag secondFlag, final boolean value) {
        boolean dataFlag = this.getDataFlag(firstFlag, secondFlag);

        if (dataFlag != value) {
            if (firstFlag.equals(EntityMetadataFlag.PLAYER_FLAGS)) {
                byte flags = this.getByte(firstFlag);
                flags ^= 1 << secondFlag.ordinal();

                this.setByte(firstFlag, flags);
            } else {
                long flags = this.getLong(firstFlag);
                flags ^= 1L << secondFlag.ordinal();

                this.setLong(firstFlag, flags);
            }
        }

        return this;
    }

    /**
     * Returns the {@link java.lang.Byte} value of the given{@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag that is the holder of the {@link java.lang.Byte} value
     *
     * @return a fresh {@link java.lang.Byte}
     */
    public byte getByte(final EntityMetadataFlag entityMetadataFlag) {
        return (byte) this.metadataValues.get(entityMetadataFlag).getValue();
    }

    /**
     * Returns the {@link java.lang.Short} value of the given{@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag that is the holder of the {@link java.lang.Short} value
     *
     * @return a fresh {@link java.lang.Short}
     */
    public short getShort(final EntityMetadataFlag entityMetadataFlag) {
        return (short) this.metadataValues.get(entityMetadataFlag).getValue();
    }

    /**
     * Returns the {@link java.lang.Float} value of the given{@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag that is the holder of the {@link java.lang.Float} value
     *
     * @return a fresh {@link java.lang.Float}
     */
    public float getFloat(final EntityMetadataFlag entityMetadataFlag) {
        return (float) this.metadataValues.get(entityMetadataFlag).getValue();
    }

    /**
     * Returns the {@link java.lang.String} value of the given{@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag that is the holder of the {@link java.lang.String} value
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getString(final EntityMetadataFlag entityMetadataFlag) {
        return (String) this.metadataValues.get(entityMetadataFlag).getValue();
    }

    /**
     * Returns the {@link java.lang.Long} value of the given{@link org.terracottamc.entity.metadata.EntityMetadataFlag}
     *
     * @param entityMetadataFlag that is the holder of the {@link java.lang.Long} value
     *
     * @return a fresh {@link java.lang.Long}
     */
    public long getLong(final EntityMetadataFlag entityMetadataFlag) {
        return (long) this.metadataValues.get(entityMetadataFlag).getValue();
    }

    /**
     * Retrieves a fresh data flag value
     *
     * @param firstFlag  that mostly stands for the flag index
     * @param secondFlag which is the flag to edit that functions as a key
     *
     * @return a fresh data flag value
     */
    public boolean getDataFlag(final EntityMetadataFlag firstFlag, final EntityFlag secondFlag) {
        return (firstFlag == EntityMetadataFlag.PLAYER_FLAGS ? this.getByte(firstFlag) & 0xff :
                this.getLong(firstFlag) & (1L << secondFlag.ordinal())) > 0;
    }

    /**
     * Retrieves all {@link org.terracottamc.entity.metadata.EntityMetadata} values
     *
     * @return a fresh {@link java.util.Map}
     */
    public Map<EntityMetadataFlag, EntityMetadataValue<?>> getMetadataValues() {
        return this.metadataValues;
    }
}