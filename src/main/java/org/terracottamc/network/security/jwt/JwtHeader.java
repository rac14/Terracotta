package org.terracottamc.network.security.jwt;

import com.google.gson.JsonObject;

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
public class JwtHeader {

    private final JsonObject headerData;
    private final JwtEncryptionAlgorithm jwtEncryptionAlgorithm;

    /**
     * Creates a new {@link org.terracottamc.network.security.jwt.JwtHeader}
     * with given {@link com.google.gson.JsonObject} header data
     *
     * @param headerData which comes with this {@link org.terracottamc.network.security.jwt.JwtHeader}
     */
    public JwtHeader(final JsonObject headerData) {
        this.headerData = headerData;
        this.jwtEncryptionAlgorithm = new JwtEncryptionAlgorithm();
    }

    /**
     * Proofs whether the header data of this {@link org.terracottamc.network.security.jwt.JwtHeader} is valid
     *
     * @return whether the header data is valid
     */
    public boolean isValid() {
        if (this.headerData == null) {
            return false;
        }

        return this.headerData.has("alg") && this.headerData.get("alg")
                .getAsString().equalsIgnoreCase("ES384") && this.headerData.has("x5u");
    }

    /**
     * Returns the data of this {@link org.terracottamc.network.security.jwt.JwtHeader}
     *
     * @return a fresh {@link com.google.gson.JsonObject}
     */
    public JsonObject getHeaderData() {
        return this.headerData;
    }

    /**
     * Obtains the {@link org.terracottamc.network.security.jwt.JwtEncryptionAlgorithm} that comes
     * with the data of this {@link org.terracottamc.network.security.jwt.JwtHeader}
     *
     * @return a fresh {@link org.terracottamc.network.security.jwt.JwtEncryptionAlgorithm}
     */
    public JwtEncryptionAlgorithm getJwtEncryptionAlgorithm() {
        return this.jwtEncryptionAlgorithm;
    }
}