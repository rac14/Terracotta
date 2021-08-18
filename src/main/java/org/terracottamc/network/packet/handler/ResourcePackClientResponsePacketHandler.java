package org.terracottamc.network.packet.handler;

import org.terracottamc.entity.player.Player;
import org.terracottamc.network.packet.ResourcePackClientResponsePacket;
import org.terracottamc.network.packet.ResourcePackDataInfoPacket;
import org.terracottamc.network.packet.ResourcePackStackPacket;
import org.terracottamc.network.packet.type.ResourcePackEntry;
import org.terracottamc.network.packet.type.ResourcePackResponseStatus;
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
public class ResourcePackClientResponsePacketHandler implements IPacketHandler<ResourcePackClientResponsePacket> {

    @Override
    public void handle(final ResourcePackClientResponsePacket packet, final Player player) {
        switch (packet.getResponseStatus()) {
            case REFUSED:
                player.disconnect(ResourcePackResponseStatus.REFUSED.name());
                break;
            case SEND_PACKS:
                for (final ResourcePackEntry resourcePackEntry : packet.getResourcePackEntries()) {
                    final ResourcePack resourcePack = player.getServer().getResourcePackManager()
                            .retrieveResourcePackById(resourcePackEntry.getUuid());

                    if (resourcePack != null) {
                        final int maxChunkSize = 1048576;

                        final ResourcePackDataInfoPacket resourcePackDataInfoPacket = new ResourcePackDataInfoPacket();
                        resourcePackDataInfoPacket.setResourcePackUuid(resourcePack.getUuid());
                        resourcePackDataInfoPacket.setMaxChunkSize(maxChunkSize);
                        resourcePackDataInfoPacket.setChunkCount((int) (resourcePack.getSize() / maxChunkSize));
                        resourcePackDataInfoPacket.setCompressedResourcePackSize(resourcePack.getSize());
                        resourcePackDataInfoPacket.setResourcePackSha256(resourcePack.getSha256());

                        player.getPlayerNetworkConnection().sendPacket(resourcePackDataInfoPacket);
                    }
                }
                break;
            case HAVE_ALL_PACKS:
                this.sendResourcePackStack(player);
                break;
            case COMPLETED:
                break;
        }
    }

    /**
     * Sends the resource pack stack to the given {@link org.terracottamc.entity.player.Player}
     *
     * @param player who should receive the stack
     */
    private void sendResourcePackStack(final Player player) {
        final ResourcePackStackPacket resourcePackStackPacket = new ResourcePackStackPacket();
        resourcePackStackPacket.setMustAccept(Server.getInstance().getServerConfigurationData().isForceResourcePacks());

        player.getPlayerNetworkConnection().sendPacket(resourcePackStackPacket);
    }
}