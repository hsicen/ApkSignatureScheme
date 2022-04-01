package com.lenovo.leos.sign;

import com.lenovo.leos.sign.v2.ApkSignatureSchemeV2Verifier;
import com.lenovo.leos.sign.v2.Base64;
import com.lenovo.leos.sign.v2.MD5Util;
import com.lenovo.leos.sign.v2.SignatureInfo;
import com.lenovo.leos.sign.v2.SignatureNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.cert.X509Certificate;

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

    /**** 返回公钥的MD5值*/
    public static String publicKeyString(String apkFile) {
        String keyString = "";

        try {
            X509Certificate[][] signs = ApkSignatureSchemeV2Verifier.verify(apkFile);
            if (signs != null && signs.length > 0) {
                String baseStr = Base64.encodeToString(signs[0][0].getPublicKey().getEncoded(), Base64.DEFAULT);
                keyString = MD5Util.encoding(baseStr);
                System.out.println("签名MD5: " + keyString);
            }
        } catch (SignatureNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return keyString;
    }
}
