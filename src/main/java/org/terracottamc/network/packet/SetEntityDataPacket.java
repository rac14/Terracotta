package org.terracottamc.network.packet;

import org.terracottamc.entity.metadata.EntityMetadata;

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
public class SetEntityDataPacket extends Packet {

    private long entityId;
    private EntityMetadata entityMetadata;
    private long tick;

    @Override
    public int getPacketId() {
        return Protocol.SET_ENTITY_DATA_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeUnsignedVarLong(this.entityId);
        this.writeEntityMetaData(this.entityMetadata.getMetadataValues());
        this.writeUnsignedVarLong(this.tick);
    }

    /**
     * Sets the entity identifier of this {@link org.terracottamc.network.packet.SetEntityDataPacket}
     *
     * @param entityId that should be set
     */
    public void setEntityId(final long entityId) {
        this.entityId = entityId;
    }

    /**
     * Sets the {@link org.terracottamc.entity.metadata.EntityMetadata}
     * of this {@link org.terracottamc.network.packet.SetEntityDataPacket}
     *
     * @param entityMetadata that should be set
     */
    public void setEntityMetadata(final EntityMetadata entityMetadata) {
        this.entityMetadata = entityMetadata;
    }

    /**
     * Sets the tick of this {@link org.terracottamc.network.packet.SetEntityDataPacket}
     *
     * @param tick that should be set
     */
    public void setTick(final long tick) {
        this.tick = tick;
    }
}