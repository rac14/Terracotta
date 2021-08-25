package org.terracottamc.network.security.jwt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

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
public class JwtSignature {

    /**
     * Retrieves whether this {@link org.terracottamc.network.security.jwt.JwtSignature} is valid
     *
     * @param publicKey       that is needed for the validation
     * @param signatureData   that should be verified
     * @param signatureDigest which is used to proof that the signature is valid
     *
     * @return the validation result of this {@link org.terracottamc.network.security.jwt.JwtSignature}
     */
    public boolean isValid(final PublicKey publicKey, final byte[] signatureData, final byte[] signatureDigest) {
        try {
            final Signature signature = Signature.getInstance("SHA384withECDSA");

            try {
                signature.initVerify(publicKey);
                signature.update(signatureData);

                return signature.verify(this.convertConcatRSToDER(signatureDigest));
            } catch (final InvalidKeyException | SignatureException e) {
                e.printStackTrace();
            }
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Signs this {@link org.terracottamc.network.security.jwt.JwtSignature}
     *
     * @param privateKey    the {@link java.security.PrivateKey} that is used to sign
     *                      this {@link org.terracottamc.network.security.jwt.JwtSignature}
     * @param signatureData the signature to be verified
     *
     * @return fresh signed {@link org.terracottamc.network.security.jwt.JwtSignature} data
     */
    public byte[] sign(final PrivateKey privateKey, final byte[] signatureData) {
        try {
            final Signature signature = Signature.getInstance("SHA384withECDSA");

            try {
                signature.initSign(privateKey);
                signature.update(signatureData);

                return this.convertDERToConcatRS(signature.sign());
            } catch (final InvalidKeyException | SignatureException e) {
                e.printStackTrace();
            }
        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    private byte[] convertDERToConcatRS(final byte[] derSignatureData) {
        int offsetR = 4;
        int lengthR = derSignatureData[offsetR - 1];
        int offsetL = offsetR + lengthR + 2;

        if (derSignatureData[offsetR] == 0x00) {
            offsetR++;
            lengthR--;
        }

        int lengthL = derSignatureData[offsetL - 1];

        if (derSignatureData[offsetL] == 0x00) {
            offsetL++;
            lengthL--;
        }

        byte[] concat = new byte[96];

        System.arraycopy(derSignatureData, offsetR, concat, 48 - lengthR, lengthR);
        System.arraycopy(derSignatureData, offsetL, concat, 96 - lengthL, lengthL);

        return concat;
    }

    private byte[] convertConcatRSToDER(byte[] concat) {
        int rawLength = concat.length >> 1;
        int offsetR = 0;

        while (concat[offsetR] == 0x00) {
            offsetR++;
        }

        int lengthR = rawLength - offsetR;
        boolean padR = (concat[offsetR] & 0x80) != 0;
        int offsetL = rawLength;

        while (concat[offsetL] == 0x00) {
            offsetL++;
        }

        int lengthL = (rawLength << 1) - offsetL;
        boolean padL = (concat[offsetL] & 0x80) != 0;
        int sigLength = 2 + lengthR + (padR ? 1 : 0) + 2 + lengthL + (padL ? 1 : 0);
        int cursor = 0;

        byte[] derSignature = new byte[2 + sigLength];
        derSignature[cursor++] = 0x30;
        derSignature[cursor++] = (byte) sigLength;
        derSignature[cursor++] = 0x02;
        derSignature[cursor++] = (byte) (lengthR + (padR ? 1 : 0));

        if (padR) {
            cursor++;
        }

        System.arraycopy(concat, offsetR, derSignature, cursor, lengthR);

        cursor += lengthR;

        derSignature[cursor++] = 0x02;
        derSignature[cursor++] = (byte) (lengthL + (padL ? 1 : 0));

        if (padL) {
            cursor++;
        }

        System.arraycopy(concat, offsetL, derSignature, cursor, lengthL);

        return derSignature;
    }
}