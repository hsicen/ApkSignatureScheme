package com.lenovo.leos.sign.v2;

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
 * description: V2SchemeUtil
 */
public class V2SchemeUtil {

    /**
     * 判断是否是V2签名
     *
     * @param apkFile 文件路径
     * @return true:是V2签名，false:不是V2签名
     * @throws IOException IO异常
     */
    public static boolean hasSignature(String apkFile) throws IOException {
        return ApkSignatureSchemeV2Verifier.hasSignature(apkFile);
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
        return ApkSignatureSchemeV2Verifier.findSignature(apk);
    }

    /**
     * 签名 block content 解析出来的证书信息
     *
     * @param apkFile apk文件路径
     * @return 证书信息
     * @throws IOException                IO异常
     * @throws SignatureNotFoundException 签名不存在异常
     */
    public static X509Certificate[][] verify(String apkFile) throws IOException, SignatureNotFoundException {
        return ApkSignatureSchemeV2Verifier.verify(apkFile);
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
            X509Certificate[][] signs = ApkSignatureSchemeV2Verifier.verify(apkFile);
            if (signs != null && signs.length > 0) {
                String baseStr = Base64.encodeToString(signs[0][0].getPublicKey().getEncoded(), Base64.DEFAULT);
                keyString = MD5Util.encoding(baseStr);
                System.out.println("v2签名MD5: " + keyString);
            }
        } catch (SignatureNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return keyString;
    }
}
