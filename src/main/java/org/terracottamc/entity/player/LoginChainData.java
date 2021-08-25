package org.terracottamc.entity.player;

import org.terracottamc.entity.player.info.DeviceInfo;

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
public class LoginChainData {

    private final int protocolVersion;
    private final String username;
    private final String xboxId;
    private final UUID uuid;
    private final String languageCode;
    private final String gameVersion;
    private final String platformOfflineId;
    private final String platformOnlineId;
    private final String selfSignedId;
    private final String serverAddress;
    private final String thirdPartyName;
    private final boolean thirdPartyNameOnly;
    private final DeviceInfo deviceInfo;

    /**
     * Creates a new {@link org.terracottamc.entity.player.LoginChainData}
     *
     * @param protocolVersion that is the protocol version of the players client
     * @param username        that is the username of the player
     * @param xboxId          that is the xbox account id of the player
     * @param uuid            the uniqueId of the player
     * @param languageCode    that is the selected language of the players client
     * @param gameVersion     that is the game version of the players client
     * @param deviceInfo      that is the information about the players device
     */
    public LoginChainData(final int protocolVersion, final String username, final String xboxId, final UUID uuid,
                          final String languageCode, final String gameVersion, final String platformOfflineId,
                          final String platformOnlineId, final String selfSignedId, final String serverAddress,
                          final String thirdPartyName, final boolean thirdPartyNameOnly, final DeviceInfo deviceInfo) {
        this.protocolVersion = protocolVersion;
        this.username = username;
        this.xboxId = xboxId;
        this.uuid = uuid;
        this.languageCode = languageCode;
        this.gameVersion = gameVersion;
        this.deviceInfo = deviceInfo;
        this.platformOfflineId = platformOfflineId;
        this.platformOnlineId = platformOnlineId;
        this.selfSignedId = selfSignedId;
        this.serverAddress = serverAddress;
        this.thirdPartyName = thirdPartyName;
        this.thirdPartyNameOnly = thirdPartyNameOnly;
    }

    /**
     * Returns the protocol version of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh protocol version number
     */
    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    /**
     * Retrieves the username of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the xbox account id of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getXboxId() {
        return this.xboxId;
    }

    /**
     * Returns the uniqueId of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.util.UUID}
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Returns the selected language of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getLanguageCode() {
        return this.languageCode;
    }

    /**
     * Returns the game version of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getGameVersion() {
        return this.gameVersion;
    }

    /**
     * Returns the platform offline id of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPlatformOfflineId() {
        return this.platformOfflineId;
    }

    /**
     * Returns the platform online id of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPlatformOnlineId() {
        return this.platformOnlineId;
    }

    /**
     * Returns the self signed id of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getSelfSignedId() {
        return this.selfSignedId;
    }

    /**
     * Returns the server address of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getServerAddress() {
        return this.serverAddress;
    }

    /**
     * Retrieves the third party name of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getThirdPartyName() {
        return this.thirdPartyName;
    }

    /**
     * Proofs whether third party name only takes place
     *
     * @return true, when third party name only takes place, otherwise false
     */
    public boolean isThirdPartyNameOnly() {
        return this.thirdPartyNameOnly;
    }

    /**
     * Retrieves the {@link org.terracottamc.entity.player.info.DeviceInfo}
     * of the {@link org.terracottamc.entity.player.Player}
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.DeviceInfo}
     */
    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }
}