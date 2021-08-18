package org.terracottamc.entity;

import org.terracottamc.util.EntityIdUtil;

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
public abstract class Entity {

    private final long entityId;

    /**
     * Creates a new {@link org.terracottamc.entity.Entity} with given runtime identifier
     */
    public Entity() {
        this.entityId = EntityIdUtil.registerEntityId(this);
    }

    /**
     * Retrieves the unique identifier of this {@link org.terracottamc.entity.Entity}
     *
     * @return a fresh entityId
     */
    public long getEntityId() {
        return this.entityId;
    }

    /**
     * Retrieves the {@link EntityType} for this {@link org.terracottamc.entity.Entity}
     *
     * @return a fresh {@link EntityType}
     */
    public abstract EntityType getEntityType();

    /**
     * Retrieves the width of this {@link org.terracottamc.entity.Entity}
     *
     * @return the width of this entity
     */
    public abstract float getWidth();

    /**
     * Retrieves the height of this {@link org.terracottamc.entity.Entity}
     *
     * @return the height of this entity
     */
    public abstract float getHeight();
}