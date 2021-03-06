package com.lenovo.leos.sign.v1;

import com.lenovo.leos.sign.SignatureNotFoundException;
import com.lenovo.leos.sign.v2.ApkSignatureSchemeV2Verifier;

import java.io.IOException;
import java.security.cert.X509Certificate;

/**
 * @author: hsicen
 * @date: 2022/4/1 10:34
 * @email: codinghuang@163.com
 * description: 签名工具类
 */
class V1SchemeUtil {

    private static String getPublicKeyString(String apkFile) {
        try {
            X509Certificate[][] signs = ApkSignatureSchemeV2Verifier.verify(apkFile);
        } catch (SignatureNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return "v1_signature_scheme_public_key_";
    }
}
