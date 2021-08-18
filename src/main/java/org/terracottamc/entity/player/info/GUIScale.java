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
public enum GUIScale {

    ULTRA_WIDE,
    WIDE,
    NORMAL;

    /**
     * Retrieves this {@link GUIScale} by their id
     *
     * @param gameUIScaleId which is used to retrieve the {@link GUIScale}
     *
     * @return a fresh {@link GUIScale}
     */
    public static GUIScale retrieveGUIScaleById(final int gameUIScaleId) {
        for (final GUIScale gameUIScale : GUIScale.values()) {
            if (gameUIScale.ordinal() == (gameUIScaleId + 2)) {
                return gameUIScale;
            }
        }

        return null;
    }
}