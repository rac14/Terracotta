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
public enum UIProfile {

    CLASSIC,
    POCKET;

    /**
     * Retrieves this {@link org.terracottamc.entity.player.info.UIProfile} by their id
     *
     * @param uiProfileId which is used to retrieve the {@link org.terracottamc.entity.player.info.UIProfile}
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.UIProfile}
     */
    public static UIProfile retrieveUIProfileById(final int uiProfileId) {
        for (final UIProfile uiProfile : UIProfile.values()) {
            if (uiProfile.ordinal() == uiProfileId) {
                return uiProfile;
            }
        }

        return null;
    }
}