package org.terracottamc.entity.player;

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
public enum GameMode {

    SURVIVAL("Survival"),
    CREATIVE("Creative"),
    ADVENTURE("Adventure"),
    SPECTATOR("Spectator");

    private final String identifier;

    GameMode(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Returns the identifier of this {@link org.terracottamc.entity.player.GameMode}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Retrieves the {@link org.terracottamc.entity.player.GameMode} type by their id
     *
     * @param identifier which is used to retrieve the {@link org.terracottamc.entity.player.GameMode}
     *
     * @return a fresh {@link org.terracottamc.entity.player.GameMode}
     */
    public static GameMode retrieveGameModeByIdentifier(final String identifier) {
        for (final GameMode gameMode : GameMode.values()) {
            if (gameMode.getIdentifier().equals(identifier)) {
                return gameMode;
            }
        }

        return null;
    }
}