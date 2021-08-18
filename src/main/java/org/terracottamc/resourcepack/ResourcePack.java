package org.terracottamc.resourcepack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
public class ResourcePack {

    private final File file;

    private final String name;
    private final String uuid;
    private final String version;
    private final long size;
    private final byte[] sha256;

    private byte[] chunk;

    /**
     * Creates a new {@link org.terracottamc.resourcepack.ResourcePack} with its attributes
     *
     * @param file    the file to read this {@link org.terracottamc.resourcepack.ResourcePack} from
     * @param name    that is the name of this {@link org.terracottamc.resourcepack.ResourcePack}
     * @param uuid    which represents the {@link java.util.UUID} of
     *                this {@link org.terracottamc.resourcepack.ResourcePack}
     * @param version which is the version of this {@link org.terracottamc.resourcepack.ResourcePack}
     * @param size    that is the disk size of this {@link org.terracottamc.resourcepack.ResourcePack}
     * @param sha256  that is the representation of the encrypted data by their algorithm sha256
     * @param chunk   which is the chunk of this {@link org.terracottamc.resourcepack.ResourcePack}
     */
    public ResourcePack(final File file, final String name, final String uuid, final String version, final long size, final byte[] sha256,
                        final byte[] chunk) {
        this.file = file;
        this.name = name;
        this.uuid = uuid;
        this.version = version;
        this.size = size;
        this.sha256 = sha256;
        this.chunk = chunk;
    }

    /**
     * Returns the name of this {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the {@link java.util.UUID} of this {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Returns the version of this {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Retrieves the disk size of this {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return a fresh resource pack size
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Retrieves the encrypted data of this {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return fresh data
     */
    public byte[] getSha256() {
        return this.sha256;
    }

    /**
     * Returns the chunk of this {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return chunk of the pack
     */
    public byte[] getChunk(final int offset, final int length) {
        if ((this.size - offset) > length) {
            this.chunk = new byte[length];
        } else {
            this.chunk = new byte[(int) (this.size - offset)];
        }

        try (final FileInputStream fileInputStream = new FileInputStream(this.file)) {
            fileInputStream.skip(offset);
            fileInputStream.read(this.chunk);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return this.chunk;
    }
}