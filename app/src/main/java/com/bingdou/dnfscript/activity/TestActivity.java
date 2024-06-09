package com.bingdou.dnfscript.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.service.DNFService;
import com.bingdou.dnfscript.tools.APPUtils;
import com.bingdou.dnfscript.tools.FloatWindowManager;
import com.bingdou.dnfscript.tools.Logger;

/**
 * 测试页面
 */
public class TestActivity extends BaseActivity {

    private static final String TAG = "TestActivity";

    private WindowManager mWindowManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.test_overlay_window).setOnClickListener(view -> {
            startOverlaySettings();
//            APPUtils.startApp(TestActivity.this, APPUtils.DNF_PACKAGE_NAME, APPUtils.DNF_CLASS_NAME);
//            APPUtils.openApp(TestActivity.this, APPUtils.DNF_PACKAGE_NAME);
        });
        findViewById(R.id.test_screen).setOnClickListener(view -> {
            startActivity(new Intent(TestActivity.this, TestRecognitionActivity.class));
        });

        findViewById(R.id.test_accessibility).setOnClickListener(view -> {
            startAccessibilitySettings();
        });
    }

    private void startDNFService() {
        Intent intent = new Intent(TestActivity.this, DNFService.class);
        startService(intent);
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
        startDNFService();
        finish();
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
        }
    }
}