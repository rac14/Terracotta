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
public class SkinAnimation {

    private final SkinImage skinImage;
    private final int type;
    private final float frames;
    private final int expression;

    /**
     * Creates a new {@link org.terracottamc.entity.player.skin.SkinAnimation}
     *
     * @param skinImage  which is used to create this {@link org.terracottamc.entity.player.skin.SkinAnimation}
     * @param type       which represents the type of the animation
     * @param frames     the amount of frames of this animation
     * @param expression the expression number of this {@link org.terracottamc.entity.player.skin.SkinAnimation}
     */
    public SkinAnimation(final SkinImage skinImage, final int type, final float frames, final int expression) {
        this.skinImage = skinImage;
        this.type = type;
        this.frames = frames;
        this.expression = expression;
    }

    /**
     * Retrieves the {@link org.terracottamc.entity.player.skin.SkinImage} of this
     * {@link org.terracottamc.entity.player.skin.SkinAnimation}
     *
     * @return a fresh {@link org.terracottamc.entity.player.skin.SkinImage}
     */
    public SkinImage getSkinImage() {
        return this.skinImage;
    }

    /**
     * Retrieves the type of this {@link org.terracottamc.entity.player.skin.SkinAnimation}
     *
     * @return a fresh type
     */
    public int getType() {
        return this.type;
    }

    /**
     * Retrieves the amount of frames of this {@link org.terracottamc.entity.player.skin.SkinAnimation}
     *
     * @return a fresh frame amount
     */
    public float getFrames() {
        return this.frames;
    }

    /**
     * Returns the expression of this {@link org.terracottamc.entity.player.skin.SkinImage}
     *
     * @return a fresh expression
     */
    public int getExpression() {
        return this.expression;
    }
}