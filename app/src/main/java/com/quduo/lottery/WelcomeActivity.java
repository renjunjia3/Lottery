package com.quduo.lottery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 欢迎界面
 * Created by scene on 2017/12/18.
 */

public class WelcomeActivity extends SupportActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        MyApplication.getInstance().setResourceId(getResouyceId());
        applyExternalStorage();
    }

    //申请内部存储权限
    private void applyExternalStorage() {
        List<PermissionItem> permissons = new ArrayList<>();
        permissons.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "内部存储权限", R.drawable.permission_ic_storage));
        HiPermission.create(WelcomeActivity.this)
                .title("权限申请")
                .style(R.style.PermissionDefaultNormalStyle)
                .permissions(permissons)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        if (!HiPermission.checkPermission(WelcomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            MyApplication.getInstance().exit();
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (HiPermission.checkPermission(WelcomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            MyApplication.getInstance().exit();
                        }
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }

    /**
     * Case By:获取渠道
     * Author: scene on 2017/5/19 10:46
     */
    private String getResouyceId() {
        String resultData = "";
        try {
            PackageManager packageManager = getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        int str = applicationInfo.metaData.getInt("CHANNEL", 1001);
                        resultData = String.valueOf(str);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }
}
