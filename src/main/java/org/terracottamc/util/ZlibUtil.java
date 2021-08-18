package org.terracottamc.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
public class ZlibUtil {

    private static final ThreadLocal<Deflater> deflater = ThreadLocal.withInitial(() -> new Deflater(7, true));
    private static final ThreadLocal<Inflater> inflater = ThreadLocal.withInitial(() -> new Inflater(true));
    private static final ThreadLocal<byte[]> buffer = ThreadLocal.withInitial(() -> new byte[2 * 1024 * 1024]);

    /**
     * Compresses the given data byte array with the given compressionLevel
     *
     * @param data which represents the byte array
     *
     * @return a fresh compressed data
     */
    public static byte[] compress(final byte[] data) {
        final Deflater deflater = ZlibUtil.deflater.get();
        deflater.reset();
        deflater.setInput(data);
        deflater.finish();

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byteArrayOutputStream.reset();

        final byte[] buffer = ZlibUtil.buffer.get();

        while (!deflater.finished()) {
            final int length = deflater.deflate(buffer);

            byteArrayOutputStream.write(buffer, 0, length);
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Decompresses the given data byte array
     *
     * @param data which should be decompressed
     *
     * @return a fresh decompressed data
     */
    public static byte[] decompress(final byte[] data) {
        final Inflater inflater = ZlibUtil.inflater.get();
        inflater.setInput(data);

        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.reset();

        final byte[] buffer = ZlibUtil.buffer.get();

        while (!inflater.finished() && inflater.getTotalIn() < data.length) {
            int length = 0;

            try {
                length = inflater.inflate(buffer);
            } catch (final DataFormatException e) {
                e.printStackTrace();
            }

            byteArrayOutputStream.write(buffer, 0, length);
        }

        inflater.reset();

        return byteArrayOutputStream.toByteArray();
    }
}