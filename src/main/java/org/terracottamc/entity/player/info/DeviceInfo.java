package org.terracottamc.entity.player.info;

import lombok.ToString;

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
@ToString
public class DeviceInfo {

    private final String deviceName;
    private final String deviceId;
    private final long clientId;
    private final DeviceOs deviceOs;
    private final GUIScale guiScale;
    private final UIProfile uiProfile;
    private final InputHardware currentInputDevice;
    private final InputHardware defaultInputDevice;

    /**
     * Creates a new {@link org.terracottamc.entity.player.info.DeviceInfo}
     *
     * @param deviceName         the name of the device
     * @param deviceId           the id of the device
     * @param clientId           the client identifier
     * @param deviceOs           the operating system of the device
     * @param guiScale           the selected ui scale in game
     * @param uiProfile          the selected ui profile in the game
     * @param currentInputDevice the input device currently used in the game
     * @param defaultInputDevice the default input device
     */
    public DeviceInfo(final String deviceName, final String deviceId, final long clientId, final DeviceOs deviceOs,
                      final GUIScale guiScale, final UIProfile uiProfile, final InputHardware currentInputDevice,
                      final InputHardware defaultInputDevice) {
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.clientId = clientId;
        this.deviceOs = deviceOs;
        this.guiScale = guiScale;
        this.uiProfile = uiProfile;
        this.currentInputDevice = currentInputDevice;
        this.defaultInputDevice = defaultInputDevice;
    }

    /**
     * Returns the name of the device
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getDeviceName() {
        return this.deviceName;
    }

    /**
     * Returns the id of the device
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Returns the client identifier
     *
     * @return a fresh client id
     */
    public long getClientId() {
        return this.clientId;
    }

    /**
     * Returns the operating system of the device
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.DeviceOs}
     */
    public DeviceOs getDeviceOs() {
        return this.deviceOs;
    }

    /**
     * Returns the selected ui scale
     *
     * @return a fresh {@link GUIScale}
     */
    public GUIScale getGuiScale() {
        return this.guiScale;
    }

    /**
     * Returns the selected ui profile
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.UIProfile}
     */
    public UIProfile getUIProfile() {
        return this.uiProfile;
    }

    /**
     * Returns the current input device
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.InputHardware}
     */
    public InputHardware getCurrentInputDevice() {
        return this.currentInputDevice;
    }

    /**
     * Returns the default input device
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.InputHardware}
     */
    public InputHardware getDefaultInputDevice() {
        return this.defaultInputDevice;
    }
}