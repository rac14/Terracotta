package org.terracottamc.entity;

import org.terracottamc.entity.player.skin.Skin;

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
public class EntityHuman extends EntityLiving {

    private UUID uuid;
    private Skin skin;

    public EntityHuman() {
        this.uuid = UUID.randomUUID();
        this.skin = new Skin();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    @Override
    public float getWidth() {
        return 0.6f;
    }

    @Override
    public float getHeight() {
        return 1.8f;
    }

    /**
     * Retrieves the {@link java.util.UUID} of this {@link org.terracottamc.entity.EntityHuman}
     *
     * @return a fresh {@link java.util.UUID}
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Retrieves the {@link org.terracottamc.entity.player.skin.Skin} of this {@link org.terracottamc.entity.EntityHuman}
     *
     * @return a fresh {@link org.terracottamc.entity.player.skin.Skin}
     */
    public Skin getSkin() {
        return this.skin;
    }

    /**
     * Updates the {@link java.util.UUID} of this {@link org.terracottamc.entity.EntityHuman}
     *
     * @param uuid which represents the updated uuid value
     */
    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Updates the {@link org.terracottamc.entity.player.skin.Skin} of this {@link org.terracottamc.entity.Entity}
     *
     * @param skin which stands for the updated {@link org.terracottamc.entity.player.skin.Skin}
     */
    public void setSkin(final Skin skin) {
        this.skin = skin;
    }
}