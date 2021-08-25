package org.terracottamc.network.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
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
public class MojangSecurityKeyFactory {

    private KeyFactory keyFactory;

    /**
     * Creates a new {@link org.terracottamc.network.security.MojangSecurityKeyFactory}
     */
    public MojangSecurityKeyFactory() {
        try {
            this.keyFactory = KeyFactory.getInstance("EC");
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a public key from its encoded jwt data representation
     *
     * @param serializedJwt which is required to build the {@link java.security.PublicKey}
     *
     * @return a fresh generated {@link java.security.PublicKey}
     */
    public PublicKey generatePublicKey(final String serializedJwt) {
        final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(serializedJwt));

        try {
            return this.keyFactory.generatePublic(keySpec);
        } catch (final InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return null;
    }
}