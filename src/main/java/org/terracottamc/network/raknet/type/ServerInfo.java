package org.terracottamc.network.raknet.type;

import org.terracottamc.entity.player.GameMode;
import org.terracottamc.network.packet.Protocol;
import org.terracottamc.server.Server;

import java.util.StringJoiner;

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
public class ServerInfo {

    private long serverId;
    private String motd;
    private String submotd;
    private int maxPlayers;
    private GameMode defaultGameMode;

    /**
     * Retrieves the id of the {@link org.terracottamc.network.raknet.type.ServerInfo}
     *
     * @return the server identifier
     */
    public long getServerId() {
        return this.serverId;
    }

    /**
     * Retrieves the motd of the {@link org.terracottamc.network.raknet.type.ServerInfo}
     *
     * @return the server motd
     */
    public String getMotd() {
        return this.motd;
    }

    /**
     * Retrieves the submotd of the {@link org.terracottamc.network.raknet.type.ServerInfo}
     *
     * @return the servers submotd
     */
    public String getSubmotd() {
        return this.submotd;
    }

    /**
     * Retrieves the amount of maximum players of the {@link org.terracottamc.network.raknet.type.ServerInfo}
     *
     * @return the amount of maximum players
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * Retrieves the default {@link org.terracottamc.entity.player.GameMode}
     * of the {@link org.terracottamc.network.raknet.type.ServerInfo}
     *
     * @return a fresh {@link org.terracottamc.entity.player.GameMode}
     */
    public GameMode getDefaultGameMode() {
        return this.defaultGameMode;
    }

    /**
     * Updates the id of the {@link org.terracottamc.server.Server}
     *
     * @param serverId which should be updated
     */
    public void setServerId(final long serverId) {
        this.serverId = serverId;
    }

    /**
     * Updates the motd of the {@link org.terracottamc.server.Server}
     *
     * @param motd which should be updated
     */
    public void setMotd(final String motd) {
        this.motd = motd;
    }

    /**
     * Updates the submotd of the {@link org.terracottamc.server.Server}
     *
     * @param submotd which should be updated
     */
    public void setSubmotd(final String submotd) {
        this.submotd = submotd;
    }

    /**
     * Updates the amount maximum players of the {@link org.terracottamc.server.Server}
     *
     * @param maxPlayers which should be updated
     */
    public void setMaxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Updates the default {@link org.terracottamc.entity.player.GameMode} of the {@link org.terracottamc.server.Server}
     *
     * @param defaultGameMode which should be updated
     */
    public void setDefaultGameMode(final GameMode defaultGameMode) {
        this.defaultGameMode = defaultGameMode;
    }

    @Override
    public String toString() {
        final StringJoiner stringJoiner = new StringJoiner(";");
        stringJoiner.add("MCPE");
        stringJoiner.add(this.motd);
        stringJoiner.add(Integer.toString(Protocol.CURRENT_PROTOCOL));
        stringJoiner.add(Protocol.MINECRAFT_VERSION);
        stringJoiner.add(Integer.toString(Server.getInstance().getPlayers().size()));
        stringJoiner.add(Integer.toString(this.maxPlayers));
        stringJoiner.add(Long.toString(this.serverId));
        stringJoiner.add(this.submotd);
        stringJoiner.add(this.defaultGameMode.getIdentifier());
        stringJoiner.add("1");

        return stringJoiner.toString();
    }
}