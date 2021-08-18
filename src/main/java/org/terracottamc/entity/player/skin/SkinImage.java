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
public class SkinImage {

    private static final int SINGLE_SKIN_SIZE = 8192;
    private static final int DOUBLE_SKIN_SIZE = 16384;
    private static final int SKIN_128_64_SIZE = 32768;
    private static final int SKIN_128_128_SIZE = 65536;

    private final int width;
    private final int height;
    private final byte[] data;

    /**
     * Creates a new {@link org.terracottamc.entity.player.skin.SkinImage} with given width, height and image data
     *
     * @param width  which represents the width of this {@link org.terracottamc.entity.player.skin.SkinImage}
     * @param height which is the height of this {@link org.terracottamc.entity.player.skin.SkinImage}
     * @param data   that is the {@link org.terracottamc.entity.player.skin.SkinImage} data
     */
    public SkinImage(final int width, final int height, final byte[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    /**
     * Retrieves the width of this {@link org.terracottamc.entity.player.skin.SkinImage}
     *
     * @return the width of this {@link org.terracottamc.entity.player.skin.SkinImage}
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Retrieves the height of this {@link org.terracottamc.entity.player.skin.SkinImage}
     *
     * @return the width of this {@link org.terracottamc.entity.player.skin.SkinImage}
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Retrieves the data of this {@link org.terracottamc.entity.player.skin.SkinImage}
     *
     * @return the data of this {@link org.terracottamc.entity.player.skin.SkinImage}
     */
    public byte[] getData() {
        return this.data;
    }

    /**
     * Returns a new {@link org.terracottamc.entity.player.skin.SkinImage} based on the length of the given data
     *
     * @param data which is used to create the {@link org.terracottamc.entity.player.skin.SkinImage}
     *
     * @return a fresh {@link org.terracottamc.entity.player.skin.SkinImage}
     */
    public static SkinImage getImage(final byte[] data) {
        switch (data.length) {
            case SkinImage.SINGLE_SKIN_SIZE:
                return new SkinImage(64, 32, data);
            case SkinImage.DOUBLE_SKIN_SIZE:
                return new SkinImage(64, 64, data);
            case SkinImage.SKIN_128_64_SIZE:
                return new SkinImage(128, 64, data);
            case SkinImage.SKIN_128_128_SIZE:
                return new SkinImage(128, 128, data);
            default:
                return new SkinImage(0, 0, new byte[0]);
        }
    }
}