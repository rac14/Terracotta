package org.terracottamc.network.packet.handler;

import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.ResourcePackChunkDataPacket;
import org.terracottamc.network.packet.ResourcePackChunkRequestPacket;
import org.terracottamc.resourcepack.ResourcePack;
import org.terracottamc.server.Server;

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
public class ResourcePackChunkRequestPacketHandler implements IPacketHandler<ResourcePackChunkRequestPacket> {

    @Override
    public void handle(final ResourcePackChunkRequestPacket packet, final Player player) {
        final ResourcePack resourcePack = Server.getInstance().getResourcePackManager()
                .retrieveResourcePackById(packet.getResourcePackUuid());

        if (resourcePack != null) {
            final int size = 1048576;

            final ResourcePackChunkDataPacket resourcePackChunkDataPacket = new ResourcePackChunkDataPacket();
            resourcePackChunkDataPacket.setResourcePackUuid(packet.getResourcePackUuid());
            resourcePackChunkDataPacket.setChunkIndex(packet.getChunkIndex());
            resourcePackChunkDataPacket.setDownloadProgress((long) size * packet.getChunkIndex());
            resourcePackChunkDataPacket.setDataToDownload(resourcePack.getChunk(size * packet.getChunkIndex(), size));

            player.getPlayerNetworkConnection().sendPacket(resourcePackChunkDataPacket);
        }
    }
}