package org.terracottamc.network.security.jwt;

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
public class JwtEncryptionAlgorithm {

    private final JwtSignature jwtSignature;

    /**
     * Creates a new {@link org.terracottamc.network.security.jwt.JwtEncryptionAlgorithm}
     */
    public JwtEncryptionAlgorithm() {
        this.jwtSignature = new JwtSignature();
    }

    /**
     * Retrieves the {@link org.terracottamc.network.security.jwt.JwtSignature}
     * of this {@link org.terracottamc.network.security.jwt.JwtEncryptionAlgorithm}
     *
     * @return a fresh {@link org.terracottamc.network.security.jwt.JwtSignature}
     */
    public JwtSignature getJwtSignature() {
        return this.jwtSignature;
    }
}