package com.bingdou.dnfscript.activity;

import android.app.Activity;
import android.os.Bundle;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.service.FightHelper;

/**
 * 录屏Activity
 */
public class ScreenCaptureActivity extends BaseActivity {

    private static final String TAG = "ScreenCaptureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_capture);

    }

    @Override
    protected void onResume() {
        super.onResume();
        FightHelper.getInstance().judgeIsFight(this);
    }
}