package com.lenovo.leos.sign;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: hsicen
 * @date: 2022/4/1 17:43
 * @email: codinghuang@163.com
 * description: MD5工具类
 */
@SuppressWarnings("ALL")
public class MD5Util {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String encodingAlgorithm = "MD5";

    public static String encoding(byte[] bs) {
        if (bytesEmpty(bs)) return "";

        try {
            MessageDigest mdTemp = MessageDigest.getInstance(encodingAlgorithm);
            mdTemp.update(bs);
            return toHexString(mdTemp.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte value : b) {
            sb.append(HEX_DIGITS[(value & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[value & 0x0f]);
        }
        return sb.toString();
    }

    public static String encoding(String text) {
        if (isEmpty(text)) {
            return null;
        }
        return encoding(text.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeTwice(String text) {
        if (isEmpty(text)) return null;

        String md5Once = encoding(text.getBytes(StandardCharsets.UTF_8));
        if (md5Once != null) {
            return encoding(md5Once.getBytes(StandardCharsets.UTF_8));
        }
        return null;
    }

    public static String md5sum(File file) {
        try (InputStream fis = new FileInputStream(file)) {
            return md5sum(fis);
        } catch (Exception e) {
            return "";
        }
    }

    public static String md5sum(InputStream fis) {
        byte[] buffer = new byte[32 * 1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance(encodingAlgorithm);
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            return toHexString(md5.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            return "";
        }
    }

    static boolean bytesEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
