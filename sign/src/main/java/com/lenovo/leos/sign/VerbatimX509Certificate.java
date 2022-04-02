package com.lenovo.leos.sign;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * For legacy reasons we need to return exactly the original encoded certificate bytes, instead
 * of letting the underlying implementation have a shot at re-encoding the data.
 */
public class VerbatimX509Certificate extends WrappedX509Certificate {
    private final byte[] mEncodedVerbatim;
    private int mHash = -1;

    public VerbatimX509Certificate(X509Certificate wrapped, byte[] encodedVerbatim) {
        super(wrapped);
        this.mEncodedVerbatim = encodedVerbatim;
    }

    @Override
    public byte[] getEncoded() throws CertificateEncodingException {
        return mEncodedVerbatim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VerbatimX509Certificate)) return false;

        try {
            byte[] a = this.getEncoded();
            byte[] b = ((VerbatimX509Certificate) o).getEncoded();
            return Arrays.equals(a, b);
        } catch (CertificateEncodingException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (mHash == -1) {
            try {
                mHash = Arrays.hashCode(this.getEncoded());
            } catch (CertificateEncodingException e) {
                mHash = 0;
            }
        }
        return mHash;
    }
}

