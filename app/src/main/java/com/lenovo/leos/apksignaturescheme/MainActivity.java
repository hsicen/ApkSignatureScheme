package com.lenovo.leos.apksignaturescheme;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.leos.apksignaturescheme.databinding.ActivityMainBinding;
import com.lenovo.leos.sign.V1SchemeUtil;
import com.lenovo.leos.sign.V2SchemeUtil;
import com.lenovo.leos.sign.v2.ApkSignatureSchemeV2Verifier;
import com.lenovo.leos.sign.v2.Base64;
import com.lenovo.leos.sign.v2.SignatureInfo;
import com.lenovo.leos.sign.v2.SignatureNotFoundException;

import java.io.IOException;
import java.security.Permissions;

/**
 * @author: hsicen
 * @date: 2022/3/31 17:05
 * @email: codinghuang@163.com
 * description: apk 签名信息获取
 */
@SuppressLint("SdCardPath")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.btnV1Sign.setOnClickListener(v -> {
            mBinding.tvV1Sign.setText(V1SchemeUtil.getPublicKeyString("/sdcard/Download/test_v1.apk"));
        });

        mBinding.btnV2Sign.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }

            String apkPath = "/storage/emulated/0/Download/com.taobao.taobao.apk";
            try {
                boolean hasV2 = V2SchemeUtil.hasSignature(apkPath);


                if (hasV2) {
                    mBinding.tvV2Sign.setText(V2SchemeUtil.getPublicKeyString(apkPath));
                    SignatureInfo signature = V2SchemeUtil.findSignature(apkPath);
                    System.out.println("签名MD5: " + Base64.encodeToString(signature.eocd.array(), Base64.DEFAULT));
                }
            } catch (IOException | SignatureNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}