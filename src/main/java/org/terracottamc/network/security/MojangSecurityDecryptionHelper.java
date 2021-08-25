package org.terracottamc.network.security;

import org.terracottamc.server.Server;

import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;

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
public class MojangSecurityDecryptionHelper {

    private PublicKey mojangRootKey;

    /**
     * Generates the mojang root key which is needed for the decryption
     */
    public void generateMojangRootKey() {
        final String serializedJwtRoot = "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAE8ELkixyLcwlZryUQcu1TvPOmI2B7vX83ndnWRUaXm7" +
                "4wFfa5f/lwQNTfrLVHa2PmenpGI6JhIMUJaWZrjmMj90NoKNFSNBuKdm8rYiXsfaz3K36x/1U26HpG0ZxK/V1V";

        this.mojangRootKey = Server.getInstance().getMojangSecurityKeyFactory().generatePublicKey(serializedJwtRoot);
    }

    /**
     * Deserializes the {@link java.util.Base64} mojang identity public key data
     *
     * @param serializedJwt the encoded data that should be decrypted and used for the key creation
     *
     * @return a fresh generated {@link java.security.interfaces.ECPublicKey}
     */
    public ECPublicKey generateMojangIdentityPublicKey(final String serializedJwt) {
        return (ECPublicKey) Server.getInstance().getMojangSecurityKeyFactory().generatePublicKey(serializedJwt);
    }

    /**
     * Obtains the mojang root key as a {@link java.security.PublicKey}
     *
     * @return a fresh {@link java.security.PublicKey}
     */
    public PublicKey getMojangRootKey() {
        return this.mojangRootKey;
    }
}