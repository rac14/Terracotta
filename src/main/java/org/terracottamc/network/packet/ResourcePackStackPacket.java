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
public class ResourcePackStackPacket extends Packet {

    private boolean mustAccept;

    @Override
    public int getPacketId() {
        return Protocol.RESOURCE_PACK_STACK_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeBoolean(this.mustAccept);

        this.writeUnsignedVarInt(0); // behavior packs

        final Collection<ResourcePack> resourcePacks = Server.getInstance().getResourcePackManager().retrieveResourcePacks();

        this.writeUnsignedVarInt(resourcePacks.size());

        for (final ResourcePack resourcePack : resourcePacks) {
            this.writeString(resourcePack.getUuid());
            this.writeString(resourcePack.getVersion());
            this.writeString("");
        }

        this.writeString(Protocol.MINECRAFT_VERSION);
        this.writeInt(0); // length of experimental data
        this.writeBoolean(false); // experiments previously toggled
    }

    /**
     * Updates whether the client must accept the stack
     *
     * @param mustAccept which represents the updated value
     */
    public void setMustAccept(final boolean mustAccept) {
        this.mustAccept = mustAccept;
    }
}