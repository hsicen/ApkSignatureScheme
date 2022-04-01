package com.lenovo.leos.sign.v2;

/**
 * @author: hsicen
 * @date: 2022/3/31 17:28
 * @email: codinghuang@163.com
 * description: Indicates that the APK is missing a signature.
 */
public class SignatureNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public SignatureNotFoundException(String message) {
        super(message);
    }

    public SignatureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
