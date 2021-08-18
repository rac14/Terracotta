package org.terracottamc.server;

import org.terracottamc.entity.player.Player;
import org.terracottamc.logging.Logger;
import org.terracottamc.network.packet.registry.PacketRegistry;
import org.terracottamc.network.raknet.RakNetListener;
import org.terracottamc.resourcepack.ResourcePackManager;
import org.terracottamc.terminal.Terminal;
import org.terracottamc.terminal.TerminalThread;
import org.terracottamc.util.FileManagementUtil;

import java.util.ArrayList;
import java.util.Collection;
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
public class Server {

    private static Server instance;

    private final long serverId;
    private final ServerConfigurationData serverConfigurationData;
    private final PacketRegistry packetRegistry;
    private final Collection<Player> players = new ArrayList<>();
    private final Logger logger = new Logger();

    private RakNetListener rakNetListener;
    private TerminalThread terminalThread;
    private boolean running;

    private final ResourcePackManager resourcePackManager;

    /**
     * Creates a new {@link org.terracottamc.server.Server}
     */
    public Server() {
        Server.instance = this;

        this.packetRegistry = new PacketRegistry();
        this.serverId = UUID.randomUUID().getMostSignificantBits();
        this.serverConfigurationData = FileManagementUtil.getServerConfigurationData();
        this.resourcePackManager = new ResourcePackManager();
    }

    /**
     * Starts this {@link org.terracottamc.server.Server}
     */
    public void start() {
        this.running = true;

        final Terminal terminal = new Terminal();

        this.terminalThread = terminal.getThread();
        this.terminalThread.start();

        this.rakNetListener = new RakNetListener(this.serverId);
        this.rakNetListener.bind();

        this.resourcePackManager.loadResourcePacks();
    }

    /**
     * Closes this {@link org.terracottamc.server.Server}
     */
    public void shutdown() {
        Server.getInstance().getLogger().info("The server is shutting down..");

        this.terminalThread.interrupt();
        this.rakNetListener.close();
        this.running = false;
    }

    /**
     * Retrieves an instance of the fresh {@link org.terracottamc.server.Server}
     *
     * @return the fresh {@link org.terracottamc.server.Server}
     */
    public static Server getInstance() {
        return Server.instance;
    }

    /**
     * Retrieves the identifier of this {@link org.terracottamc.server.Server}
     *
     * @return a fresh long server id
     */
    public long getServerId() {
        return this.serverId;
    }

    /**
     * Returns the {@link org.terracottamc.server.ServerConfigurationData} of this {@link org.terracottamc.server.Server}
     *
     * @return a fresh {@link org.terracottamc.server.ServerConfigurationData}
     */
    public ServerConfigurationData getServerConfigurationData() {
        return this.serverConfigurationData;
    }

    /**
     * Retrieves the {@link org.terracottamc.network.packet.registry.PacketRegistry}
     * of this {@link org.terracottamc.server.Server}
     *
     * @return a fresh {@link org.terracottamc.network.packet.registry.PacketRegistry}
     */
    public PacketRegistry getPacketRegistry() {
        return this.packetRegistry;
    }

    /**
     * Retrieves all players which are currently playing on this {@link org.terracottamc.server.Server}
     *
     * @return a fresh {@link java.util.Collection} of players who are currently playing
     */
    public Collection<Player> getPlayers() {
        return this.players;
    }

    /**
     * Retrieves the {@link org.terracottamc.logging.Logger} of this {@link org.terracottamc.server.Server}
     *
     * @return a fresh {@link org.terracottamc.logging.Logger}
     */
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Retrieves whether this {@link org.terracottamc.server.Server} is still running
     *
     * @return if the server is still running
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Retrieves the {@link org.terracottamc.resourcepack.ResourcePackManager}
     * of this {@link org.terracottamc.server.Server}
     *
     * @return a fresh {@link org.terracottamc.resourcepack.ResourcePackManager}
     */
    public ResourcePackManager getResourcePackManager() {
        return this.resourcePackManager;
    }
}