package com.lenovo.leos.sign;

import com.lenovo.leos.sign.v2.ApkSignatureSchemeV2Verifier;
import com.lenovo.leos.sign.v2.SignatureInfo;
import com.lenovo.leos.sign.v2.SignatureNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * @author: hsicen
 * @date: 2022/4/1 10:23
 * @email: codinghuang@163.com
 * description: V2SchemeUtil
 */
public class V2SchemeUtil {

    public static boolean hasSignature(String apkFile) throws IOException {
        return ApkSignatureSchemeV2Verifier.hasSignature(apkFile);
    }

    public static SignatureInfo findSignature(String apkFile)
            throws IOException, SignatureNotFoundException {
        RandomAccessFile apk = new RandomAccessFile(apkFile, "r");
        return ApkSignatureSchemeV2Verifier.findSignature(apk);
    }

    public static X509Certificate[][] verify(String apkFile) throws IOException, SignatureNotFoundException {
        return ApkSignatureSchemeV2Verifier.verify(apkFile);
    }

    public static String getPublicKeyString(String apkFile) {
        try {
            X509Certificate[][] signs = ApkSignatureSchemeV2Verifier.verify(apkFile);
            if (signs != null && signs.length > 0) {
                System.out.println(Arrays.deepToString(signs));
            }
        } catch (SignatureNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return "v2_signature_scheme_public_key_";
    }
}
