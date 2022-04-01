package com.lenovo.leos.sign.v3;

/**
 * @author: hsicen
 * @date: 2022/4/1 10:12
 * @email: codinghuang@163.com
 * description: APK Signature Scheme v3 verifier.
 */
public class ApkSignatureSchemeV3Verifier {
    /**
     * ID of this signature scheme as used in X-Android-APK-Signed header used in JAR signing.
     */
    public static final int SF_ATTRIBUTE_ANDROID_APK_SIGNED_ID = 3;

    private static final int APK_SIGNATURE_SCHEME_V3_BLOCK_ID = 0xf05368c0;
}
