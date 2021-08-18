package org.terracottamc.network.packet;

import org.terracottamc.resourcepack.ResourcePack;
import org.terracottamc.server.Server;

import java.util.Collection;

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
public class ResourcePacksInfoPacket extends Packet {

    private boolean forceAccept;
    private boolean scripting;
    private boolean forceServerPacks;

    @Override
    public int getPacketId() {
        return Protocol.RESOURCE_PACKS_INFO_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeBoolean(this.forceAccept);
        this.writeBoolean(this.scripting);
        this.writeBoolean(this.forceServerPacks);
        this.writeShortLE(0); // behaviour packs amount

        final Collection<ResourcePack> resourcePacks = Server.getInstance().getResourcePackManager()
                .retrieveResourcePacks();

        this.writeShortLE(resourcePacks.size());

        for (final ResourcePack resourcePack : resourcePacks) {
            this.writeString(resourcePack.getUuid());
            this.writeString(resourcePack.getVersion());
            this.writeLongLE(resourcePack.getSize());
            this.writeString(""); // encryption key
            this.writeString(""); // sub name
            this.writeString(""); // content identity
            this.writeBoolean(false); // scripting
            this.writeBoolean(false); // is raytracing capable
        }
    }

    /**
     * Sets whether the client must force accept the resource and behavior packs
     *
     * @param forceAccept which represents the updated value
     */
    public void setForceAccept(final boolean forceAccept) {
        this.forceAccept = forceAccept;
    }

    /**
     * Sets whether scripting is enabled or not
     *
     * @param scripting which represents the updated value
     */
    public void setScripting(final boolean scripting) {
        this.scripting = scripting;
    }

    /**
     * Sets whether the server packs should be forced or not
     *
     * @param forceServerPacks which represents the updated value
     */
    public void setForceServerPacks(final boolean forceServerPacks) {
        this.forceServerPacks = forceServerPacks;
    }
}