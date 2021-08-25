package org.terracottamc.network.security.jwt;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
public class Jwt {

    private JwtHeader jwtHeader;
    private JsonObject jwtPayload;
    private byte[] signatureData;
    private byte[] signatureDigest;

    /**
     * Creates a new {@link org.terracottamc.network.security.jwt.Jwt} token by their raw data
     *
     * @param jwtRaw the raw data which is needed to create this {@link org.terracottamc.network.security.jwt.Jwt}
     */
    public static Jwt read(final String jwtRaw) {
        final String[] jwtParts = jwtRaw.split("\\.");

        if (jwtParts.length == 3) {
            final JsonObject jwtHeaderRaw = new JsonParser()
                    .parse(new StringReader(new String(Base64.getDecoder().decode(jwtParts[0])))).getAsJsonObject();
            final JsonObject jwtPayloadRaw = new JsonParser()
                    .parse(new StringReader(new String(Base64.getDecoder().decode(jwtParts[1])))).getAsJsonObject();
            final String headerAndPayload = jwtParts[0] + "." + jwtParts[1];
            final String signaturePart = jwtParts[2];

            final Jwt jwt = new Jwt();
            jwt.jwtHeader = new JwtHeader(jwtHeaderRaw);
            jwt.jwtPayload = jwtPayloadRaw;
            jwt.signatureData = headerAndPayload.getBytes(StandardCharsets.UTF_8);
            jwt.signatureDigest = Base64.getUrlDecoder().decode(signaturePart);

            return jwt;
        }

        return null;
    }

    /**
     * Retrieves the {@link org.terracottamc.network.security.jwt.Jwt} of this token
     *
     * @return a fresh {@link org.terracottamc.network.security.jwt.JwtHeader}
     */
    public JwtHeader getJwtHeader() {
        return this.jwtHeader;
    }

    /**
     * Obtains the payload of this {@link org.terracottamc.network.security.jwt.Jwt}
     * as {@link com.google.gson.JsonObject}
     *
     * @return a fresh {@link com.google.gson.JsonObject} that represents the payload
     */
    public JsonObject getJwtPayload() {
        return this.jwtPayload;
    }

    /**
     * Retrieves the signature data of this {@link org.terracottamc.network.security.jwt.Jwt}
     *
     * @return fresh signature data
     */
    public byte[] getSignatureData() {
        return this.signatureData;
    }

    /**
     * Retrieves the signature digest of this {@link org.terracottamc.network.security.jwt.Jwt}
     *
     * @return fresh signature digest
     */
    public byte[] getSignatureDigest() {
        return this.signatureDigest;
    }
}