package org.terracottamc.network.packet.registry;

import org.terracottamc.network.packet.ClientCacheStatusPacket;
import org.terracottamc.network.packet.DisconnectPacket;
import org.terracottamc.network.packet.LoginPacket;
import org.terracottamc.network.packet.Packet;
import org.terracottamc.network.packet.PlayStatusPacket;
import org.terracottamc.network.packet.Protocol;
import org.terracottamc.network.packet.ResourcePackChunkDataPacket;
import org.terracottamc.network.packet.ResourcePackChunkRequestPacket;
import org.terracottamc.network.packet.ResourcePackClientResponsePacket;
import org.terracottamc.network.packet.ResourcePackDataInfoPacket;
import org.terracottamc.network.packet.ResourcePackStackPacket;
import org.terracottamc.network.packet.ResourcePacksInfoPacket;
import org.terracottamc.network.packet.ViolationWarningPacket;
import org.terracottamc.network.packet.handler.IPacketHandler;
import org.terracottamc.network.packet.handler.LoginPacketHandler;
import org.terracottamc.network.packet.handler.ResourcePackChunkRequestPacketHandler;
import org.terracottamc.network.packet.handler.ResourcePackClientResponsePacketHandler;
import org.terracottamc.network.packet.handler.ViolationWarningPacketHandler;

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
public class PacketRegistry {

    private final Map<Class<? extends Packet>, IPacketHandler<? extends Packet>> packetHandlerMap = new HashMap<>();

    /**
     * Creates a new {@link org.terracottamc.network.packet.registry.PacketRegistry}
     */
    public PacketRegistry() {
        this.registerPacketHandlers();
    }

    /**
     * Retrieves a {@link org.terracottamc.network.packet.Packet} by their id
     *
     * @param packetId which is used to retrieve the {@link org.terracottamc.network.packet.Packet}
     *
     * @return a fresh {@link org.terracottamc.network.packet.Packet}
     */
    public Packet retrievePacketById(final int packetId) {
        switch (packetId) {
            case Protocol.LOGIN_PACKET:
                return new LoginPacket();
            case Protocol.PLAY_STATUS_PACKET:
                return new PlayStatusPacket();
            case Protocol.DISCONNECT_PACKET:
                return new DisconnectPacket();
            case Protocol.RESOURCE_PACKS_INFO_PACKET:
                return new ResourcePacksInfoPacket();
            case Protocol.RESOURCE_PACK_STACK_PACKET:
                return new ResourcePackStackPacket();
            case Protocol.RESOURCE_PACK_CLIENT_RESPONSE_PACKET:
                return new ResourcePackClientResponsePacket();
            case Protocol.RESOURCE_PACK_DATA_INFO_PACKET:
                return new ResourcePackDataInfoPacket();
            case Protocol.RESOURCE_PACK_CHUNK_DATA_PACKET:
                return new ResourcePackChunkDataPacket();
            case Protocol.RESOURCE_PACK_CHUNK_REQUEST_PACKET:
                return new ResourcePackChunkRequestPacket();
            case Protocol.CLIENT_CACHE_STATUS_PACKET:
                return new ClientCacheStatusPacket();
            case Protocol.VIOLATION_WARNING_PACKET:
                return new ViolationWarningPacket();
        }

        return null;
    }

    /**
     * Retrieves the {@link org.terracottamc.network.packet.handler.IPacketHandler} by their
     * {@link org.terracottamc.network.packet.Packet} class
     *
     * @param clazz which is needed to retrieve the {@link org.terracottamc.network.packet.Packet}
     *
     * @return a fresh {@link org.terracottamc.network.packet.handler.IPacketHandler}
     */
    public IPacketHandler<? extends Packet> retrievePacketHandlerByClass(final Class<? extends Packet> clazz) {
        if (this.packetHandlerMap.containsKey(clazz)) {
            return this.packetHandlerMap.get(clazz);
        }

        return null;
    }

    /**
     * Registers all packets with their packet handlers
     */
    private void registerPacketHandlers() {
        this.packetHandlerMap.put(LoginPacket.class, new LoginPacketHandler());
        this.packetHandlerMap.put(ResourcePackClientResponsePacket.class, new ResourcePackClientResponsePacketHandler());
        this.packetHandlerMap.put(ResourcePackChunkRequestPacket.class, new ResourcePackChunkRequestPacketHandler());
        this.packetHandlerMap.put(ViolationWarningPacket.class, new ViolationWarningPacketHandler());
    }
}