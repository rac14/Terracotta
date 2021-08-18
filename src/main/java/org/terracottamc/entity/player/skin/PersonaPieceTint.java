package org.terracottamc.entity.player.skin;

import java.util.List;

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
public class PersonaPieceTint {

    private final String pieceType;
    private final List<String> colors;

    /**
     * Creates a new {@link org.terracottamc.entity.player.skin.PersonaPieceTint} with given type and colors
     *
     * @param pieceType the type of this {@link org.terracottamc.entity.player.skin.PersonaPieceTint}
     * @param colors    the colors of this {@link org.terracottamc.entity.player.skin.PersonaPieceTint}
     */
    public PersonaPieceTint(final String pieceType, final List<String> colors) {
        this.pieceType = pieceType;
        this.colors = colors;
    }

    /**
     * Returns the type of this {@link org.terracottamc.entity.player.skin.PersonaPieceTint}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPieceType() {
        return this.pieceType;
    }

    /**
     * Returns the colors of this {@link org.terracottamc.entity.player.skin.PersonaPieceTint}
     *
     * @return a fresh {@link java.util.List}
     */
    public List<String> getColors() {
        return this.colors;
    }
}