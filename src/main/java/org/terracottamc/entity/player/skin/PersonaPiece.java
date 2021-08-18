package org.terracottamc.entity.player.skin;

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
public class PersonaPiece {

    private final String pieceId;
    private final String pieceType;
    private final String packId;
    private final String productId;
    private final boolean isDefault;

    /**
     * Creates a new {@link org.terracottamc.entity.player.skin.PersonaPiece}
     *
     * @param pieceId   which is the identifier of the {@link org.terracottamc.entity.player.skin.PersonaPiece}
     * @param pieceType which is the type of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     * @param packId    the pack id of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     * @param productId the product id of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     * @param isDefault whether this {@link org.terracottamc.entity.player.skin.PersonaPiece} is enabled by default
     */
    public PersonaPiece(final String pieceId, final String pieceType, final String packId, final String productId, final boolean isDefault) {
        this.pieceId = pieceId;
        this.pieceType = pieceType;
        this.packId = packId;
        this.productId = productId;
        this.isDefault = isDefault;
    }

    /**
     * Retrieves the id of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPieceId() {
        return this.pieceId;
    }

    /**
     * Retrieves the type of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPieceType() {
        return this.pieceType;
    }

    /**
     * Retrieves the pack id of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPackId() {
        return this.packId;
    }

    /**
     * Retrieves the product id of this {@link org.terracottamc.entity.player.skin.PersonaPiece}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Retrieves whether this {@link org.terracottamc.entity.player.skin.PersonaPiece} is default or not
     *
     * @return whether this piece is default or not
     */
    public boolean isDefault() {
        return this.isDefault;
    }
}