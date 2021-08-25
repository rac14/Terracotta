package org.terracottamc.entity;

import org.terracottamc.entity.metadata.EntityFlag;
import org.terracottamc.entity.metadata.EntityMetadata;
import org.terracottamc.entity.metadata.EntityMetadataFlag;
import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.SetEntityDataPacket;
import org.terracottamc.server.Server;
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
    private final EntityMetadata entityMetadata;

    /**
     * Creates a new {@link org.terracottamc.entity.Entity} with given runtime identifier
     */
    public Entity() {
        this.entityId = EntityIdUtil.registerEntityId(this);
        this.entityMetadata = new EntityMetadata();
        this.entityMetadata.setLong(EntityMetadataFlag.INDEX, 0);
        this.entityMetadata.setShort(EntityMetadataFlag.MAX_AIR_SUPPLY, (short) 400);
        this.entityMetadata.setShort(EntityMetadataFlag.AIR_SUPPLY, (short) 0);
        this.entityMetadata.setFloat(EntityMetadataFlag.SCALE, 1);
        this.entityMetadata.setFloat(EntityMetadataFlag.BOUNDING_BOX_WIDTH, 1);
        this.entityMetadata.setFloat(EntityMetadataFlag.BOUNDING_BOX_HEIGHT, 1);
        this.entityMetadata.setDataFlag(EntityMetadataFlag.INDEX, EntityFlag.HAS_COLLISION, true);
        this.entityMetadata.setDataFlag(EntityMetadataFlag.INDEX, EntityFlag.HAS_GRAVITY, true);
        this.entityMetadata.setDataFlag(EntityMetadataFlag.INDEX, EntityFlag.CAN_CLIMB, true);
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

    /**
     * Retrieves the unique identifier of this {@link org.terracottamc.entity.Entity}
     *
     * @return a fresh entityId
     */
    public long getEntityId() {
        return this.entityId;
    }

    /**
     * Retrieves the {@link org.terracottamc.entity.metadata.EntityMetadata}
     * of this {@link org.terracottamc.entity.Entity}
     *
     * @return a fresh {@link org.terracottamc.entity.metadata.EntityMetadata}
     */
    public EntityMetadata getEntityMetadata() {
        return this.entityMetadata;
    }

    /**
     * Retrieves the name tag of this {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getNameTag() {
        return this.entityMetadata.getString(EntityMetadataFlag.NAME_TAG);
    }

    /**
     * Retrieves whether this name tag is always visible of this {@link org.terracottamc.entity.player.Player}
     *
     * @return whether this name tag is always visible
     */
    public boolean isNameTagAlwaysVisible() {
        return this.entityMetadata.getDataFlag(EntityMetadataFlag.INDEX, EntityFlag.SHOW_ALWAYS_NAME_TAG);
    }

    /**
     * Retrieves whether this name tag is visible of this {@link org.terracottamc.entity.player.Player}
     *
     * @return whether this name tag is visible
     */
    public boolean isNameTagVisible() {
        return this.entityMetadata.getDataFlag(EntityMetadataFlag.INDEX, EntityFlag.SHOW_NAME_TAG);
    }

    /**
     * Updates the name tag of this {@link java.lang.String}
     *
     * @param nameTag which represents the updated value
     */
    public void setNameTag(final String nameTag) {
        this.updateEntityMetadata(this.entityMetadata.setString(EntityMetadataFlag.NAME_TAG, nameTag));
    }

    /**
     * Updates the name tag visibility of this {@link java.lang.String}
     *
     * @param nameTagAlwaysVisible which represents the updated value
     */
    public void setNameTagAlwaysVisible(final boolean nameTagAlwaysVisible) {
        if (nameTagAlwaysVisible != this.isNameTagAlwaysVisible()) {
            this.updateEntityMetadata(this.entityMetadata.setDataFlag(EntityMetadataFlag.INDEX,
                    EntityFlag.SHOW_ALWAYS_NAME_TAG, nameTagAlwaysVisible));
        }
    }

    /**
     * Updates the name tag visibility of this {@link java.lang.String}
     *
     * @param nameTagVisible which represents the updated value
     */
    public void setNameTagVisible(final boolean nameTagVisible) {
        if (nameTagVisible != this.isNameTagVisible()) {
            this.updateEntityMetadata(this.entityMetadata.setDataFlag(EntityMetadataFlag.INDEX,
                    EntityFlag.SHOW_NAME_TAG, nameTagVisible));
        }
    }

    /**
     * Updates the {@link org.terracottamc.entity.metadata.EntityMetadata} of this {@link org.terracottamc.entity.Entity}
     *
     * @param entityMetadata which represents the updated value
     */
    public void updateEntityMetadata(final EntityMetadata entityMetadata) {
        final SetEntityDataPacket setEntityDataPacket = new SetEntityDataPacket();
        setEntityDataPacket.setEntityId(this.entityId);
        setEntityDataPacket.setEntityMetadata(entityMetadata);
        setEntityDataPacket.setTick(0L);

        for (final Player player : Server.getInstance().getPlayers()) {
            player.getPlayerNetworkConnection().sendPacket(setEntityDataPacket);
        }
    }
}