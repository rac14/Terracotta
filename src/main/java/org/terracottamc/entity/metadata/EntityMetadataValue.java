package org.terracottamc.entity.metadata;

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
public class EntityMetadataValue<V> {

    private final EntityMetadataFlagType entityMetadataFlagType;
    private final V value;

    /**
     * Creates a new {@link org.terracottamc.entity.metadata.EntityMetadataValue<V>}
     * with given {@link org.terracottamc.entity.metadata.EntityMetadataFlagType}
     *
     * @param entityMetadataFlagType which is used to define the flag type
     *                               of this {@link org.terracottamc.entity.metadata.EntityMetadataValue}
     * @param value                  that represents the value that is hold
     */
    public EntityMetadataValue(final EntityMetadataFlagType entityMetadataFlagType, final Object value) {
        this.entityMetadataFlagType = entityMetadataFlagType;
        this.value = (V) value;
    }

    /**
     * Returns the {@link org.terracottamc.entity.metadata.EntityMetadataFlagType}
     * of this {@link org.terracottamc.entity.metadata.EntityMetadataValue}
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadataFlagType}
     */
    public EntityMetadataFlagType getEntityMetadataFlagType() {
        return this.entityMetadataFlagType;
    }

    /**
     * Returns the value of this {@link org.terracottamc.entity.metadata.EntityMetadataValue}
     *
     * @return a fresh value
     */
    public Object getValue() {
        return this.value;
    }
}