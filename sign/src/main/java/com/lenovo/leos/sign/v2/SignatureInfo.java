package com.lenovo.leos.sign.v2;

import java.nio.ByteBuffer;

/**
 * @author: hsicen
 * @date: 2022/3/31 17:30
 * @email: codinghuang@163.com
 * description:
 * APK Signature Scheme v2 block and additional information relevant to verifying the signatures
 * contained in the block against the file.
 */
public class SignatureInfo {

    /**
     * Contents of APK Signature Scheme v2 block.
     */
    public final ByteBuffer signatureBlock;

    /**
     * Position of the APK Signing Block in the file.
     */
    public final long apkSigningBlockOffset;

    /**
     * Position of the ZIP Central Directory in the file.
     */
    public final long centralDirOffset;

    /**
     * Position of the ZIP End of Central Directory (EoCD) in the file.
     */
    public final long eocdOffset;

    /**
     * Contents of ZIP End of Central Directory (EoCD) of the file.
     */
    public final ByteBuffer eocd;

    SignatureInfo(ByteBuffer signatureBlock, long apkSigningBlockOffset, long centralDirOffset, long eocdOffset, ByteBuffer eocd) {
        this.signatureBlock = signatureBlock;
        this.apkSigningBlockOffset = apkSigningBlockOffset;
        this.centralDirOffset = centralDirOffset;
        this.eocdOffset = eocdOffset;
        this.eocd = eocd;
    }
}
