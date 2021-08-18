package org.terracottamc.server;

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
public class ServerConfigurationData {

    private String address;
    private int port;
    private int maxPlayers;
    private String motd;
    private String submotd;
    private String defaultGameMode;
    private boolean forceResourcePacks;

    /**
     * Retrieves the servers address
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Retrieves the servers port
     *
     * @return a fresh int value
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Retrieves the maximum players of the server
     *
     * @return a fresh int value
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * Retrieves the message of the day of the serer
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getMotd() {
        return this.motd;
    }

    /**
     * Retrieves the submotd of the server
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getSubmotd() {
        return this.submotd;
    }

    /**
     * Retrieves the default {@link org.terracottamc.entity.player.GameMode} of the server
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getDefaultGameMode() {
        return this.defaultGameMode;
    }

    /**
     * Retrieves whether the {@link org.terracottamc.entity.player.Player}
     * should force accept the server's resource packs
     *
     * @return whether the resource packs which are sent by the server should be accepted by the player
     */
    public boolean isForceResourcePacks() {
        return this.forceResourcePacks;
    }

    /**
     * Updates the address of the server
     *
     * @param address which should be updated
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * Updates the port of the server
     *
     * @param port which should be updated
     */
    public void setPort(final int port) {
        this.port = port;
    }

    /**
     * Updates the max players of the server
     *
     * @param maxPlayers which should be updated
     */
    public void setMaxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Updates the message of the day of the server
     *
     * @param motd which should be updated
     */
    public void setMotd(final String motd) {
        this.motd = motd;
    }

    /**
     * Updates the submotd of the server
     *
     * @param submotd which should be updated
     */
    public void setSubmotd(final String submotd) {
        this.submotd = submotd;
    }

    /**
     * Updates the default {@link org.terracottamc.entity.player.GameMode} of the server
     *
     * @param defaultGameMode which should be updated
     */
    public void setDefaultGameMode(final String defaultGameMode) {
        this.defaultGameMode = defaultGameMode;
    }

    /**
     * Updates whether the resource packs should be forced to the {@link org.terracottamc.entity.player.Player}
     *
     * @param forceResourcePacks which represents the updated value
     */
    public void setForceResourcePacks(final boolean forceResourcePacks) {
        this.forceResourcePacks = forceResourcePacks;
    }
}