package com.bingdou.dnfscript.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.service.DNFService;
import com.bingdou.dnfscript.tools.FloatWindowManager;
import com.bingdou.dnfscript.tools.Logger;

/**
 * 主页
 */
public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn_start).setOnClickListener(view -> {
            startAccessibilitySettings();
        });
        findViewById(R.id.btn_open_test).setOnClickListener(view -> {
            startActivity(new Intent(this, TestActivity.class));
        });

    }

    private void startOverlaySettings() {
        FloatWindowManager floatWindowManager = FloatWindowManager.getInstance();
        if (floatWindowManager.requestPermission(this)) {
            Logger.d(TAG, "requestPermission");
            FloatWindowManager.getInstance().initManager(this);
            FloatWindowManager.getInstance().showFloatWindow();
        } else {
            Logger.d(TAG, "requestPermission is false");
            FloatWindowManager.getInstance().initManager(this);
            FloatWindowManager.getInstance().showFloatWindow();
        }
        Intent intent = new Intent(this, ScreenCaptureActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        startDNFService();
//        finish();
    }

    private void startDNFService() {
        Intent intent = new Intent(this, DNFService.class);
        startService(intent);
    }

    private void startAccessibilitySettings() {
        // 判断无障碍功能是否已经开启
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(this.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        // 如果无障碍功能未开启，则跳转到无障碍功能设置页面
        if (accessibilityEnabled != 1) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        } else {
            startOverlaySettings();
        }
    }
}