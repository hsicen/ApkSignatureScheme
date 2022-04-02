package com.lenovo.leos.sign.v3;

import com.lenovo.leos.sign.Base64;
import com.lenovo.leos.sign.MD5Util;
import com.lenovo.leos.sign.SignatureInfo;
import com.lenovo.leos.sign.SignatureNotFoundException;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.cert.X509Certificate;

/**
 * @author: hsicen
 * @date: 2022/4/1 10:23
 * @email: codinghuang@163.com
 * description: V3SchemeUtil
 */
@SuppressWarnings("ALL")
public class V3SchemeUtil {

    /**
     * 判断是否是V3签名
     *
     * @param apkFile 文件路径
     * @return true:是V3签名，false:不是V3签名
     * @throws IOException IO异常
     */
    public static boolean hasSignature(String apkFile) throws IOException {
        return ApkSignatureSchemeV3Verifier.hasSignature(apkFile);
    }

    /**
     * 获取签名block content信息
     *
     * @param apkFile apk文件路径
     * @return 签名信息
     * @throws IOException                IO异常
     * @throws SignatureNotFoundException 签名不存在异常
     */
    public static SignatureInfo findSignature(String apkFile)
            throws IOException, SignatureNotFoundException {
        RandomAccessFile apk = new RandomAccessFile(apkFile, "r");
        return ApkSignatureSchemeV3Verifier.findSignature(apk);
    }

    /**
     * 签名 block content 解析出来的证书信息
     *
     * @param apkFile apk文件路径
     * @return 证书信息
     * @throws IOException                IO异常
     * @throws SignatureNotFoundException 签名不存在异常
     */
    public static ApkSignatureSchemeV3Verifier.VerifiedSigner verify(String apkFile) throws IOException, SignatureNotFoundException {
        return ApkSignatureSchemeV3Verifier.verify(apkFile);
    }

    /**
     * 获取第一个签名证书公钥的 MD5 值
     *
     * @param apkFile apk文件路径
     * @return MD5 值
     */
    public static String publicKeyString(String apkFile) {
        String keyString = "";

        try {
            ApkSignatureSchemeV3Verifier.VerifiedSigner signs = ApkSignatureSchemeV3Verifier.verify(apkFile);
            if (signs.certs != null && signs.certs.length > 0) {
                X509Certificate[] certs = signs.certs;

                String baseStr = Base64.encodeToString(certs[0].getPublicKey().getEncoded(), Base64.DEFAULT);
                keyString = MD5Util.encoding(baseStr);
                System.out.println("v3签名MD5: " + keyString);
            }
        } catch (SignatureNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return keyString;
    }
}
