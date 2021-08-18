package org.terracottamc.entity.player.info;

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
public enum DeviceOs {

    ANDROID,
    IOS,
    MAC_OS,
    FIRE_OS,
    SAMSUNG_GEAR_VR,
    MICROSOFT_HOLOLENS,
    WINDOWS,
    WINDOWS_x32,
    DEDICATED,
    TVOS,
    PLAYSTATION,
    NINTENDO,
    XBOX,
    WINDOWS_PHONE;

    /**
     * Retrieves this {@link org.terracottamc.entity.player.info.DeviceOs} by their id
     *
     * @param deviceOsId which is used to retrieve the {@link org.terracottamc.entity.player.info.DeviceOs}
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.DeviceOs}
     */
    public static DeviceOs retrieveDeviceOsById(final int deviceOsId) {
        for (final DeviceOs deviceOs : DeviceOs.values()) {
            if (deviceOs.ordinal() == (deviceOsId - 1)) {
                return deviceOs;
            }
        }

        return null;
    }
}