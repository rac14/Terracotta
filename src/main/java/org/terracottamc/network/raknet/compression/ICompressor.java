package org.terracottamc.network.raknet.compression;

import io.netty.buffer.ByteBuf;

import java.io.Closeable;
import java.util.zip.DataFormatException;

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
public interface ICompressor extends Closeable {

    /**
     * Processes the compression or decompression of this {@link org.terracottamc.network.raknet.compression.ICompressor}
     *
     * @param inputBuffer  which represents the input {@link io.netty.buffer.ByteBuf}
     * @param outputBuffer which represents the output {@link io.netty.buffer.ByteBuf}
     *
     * @throws DataFormatException which can be thrown when a data error has been occurred
     */
    void process(final ByteBuf inputBuffer, final ByteBuf outputBuffer) throws DataFormatException;
}