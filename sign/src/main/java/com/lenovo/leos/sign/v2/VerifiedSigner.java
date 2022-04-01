package com.lenovo.leos.sign.v2;

import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * @author: hsicen
 * @date: 2022/3/31 17:53
 * @email: codinghuang@163.com
 * description: Verified APK Signature Scheme v2 signer.
 */
public class VerifiedSigner {
    public final X509Certificate[][] certs;

    public final byte[] verityRootHash;
    // Algorithm -> digest map of signed digests in the signature.
    // All these are verified if requested.
    public final Map<Integer, byte[]> contentDigests;

    public VerifiedSigner(X509Certificate[][] certs, byte[] verityRootHash,
                          Map<Integer, byte[]> contentDigests) {
        this.certs = certs;
        this.verityRootHash = verityRootHash;
        this.contentDigests = contentDigests;
    }
}
