package com.bingdou.dnfscript.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.service.DNFService;
import com.bingdou.dnfscript.tools.FloatWindowManager;
import com.bingdou.dnfscript.tools.Logger;

/**
 * 获取各种坐标测试类
 */
public class GetCoordinateActivity extends BaseActivity implements View.OnTouchListener {

    private static final String TAG = "GetCoordinateActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coordinate);
        findViewById(R.id.ll_root).setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            /**
             * 点击的开始位置
             */
            case MotionEvent.ACTION_DOWN:
                Logger.d(TAG, "起始位置：(" + event.getX() + " , " + event.getY() + ")");
                Toast.makeText(this, "起始位置：(" + event.getX() + " , " + event.getY()  + ")", Toast.LENGTH_SHORT).show();
                break;
            /**
             * 触屏实时位置
             */
            case MotionEvent.ACTION_MOVE:
                Logger.d(TAG, "实时位置：(" + event.getX() + " , " + event.getY() + ")");
                Toast.makeText(this, "实时位置：(" + event.getX() + " , " + event.getY()  + ")", Toast.LENGTH_SHORT).show();
                break;
            /**
             * 离开屏幕的位置
             */
            case MotionEvent.ACTION_UP:
                Logger.d(TAG, "结束位置：(" + event.getX() + " , " + event.getY() + ")");
                Toast.makeText(this, "结束位置：(" + event.getX() + " , " + event.getY()  + ")", Toast.LENGTH_SHORT).show();
                Logger.d(TAG, "==================================================");
                break;
            default:
                break;
        }
        return true;
    }
}