package com.bingdou.dnfscript.tools;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.activity.HomeActivity;
import com.bingdou.dnfscript.activity.TestActivity;
import com.bingdou.dnfscript.activity.TestRecognitionActivity;
import com.bingdou.dnfscript.service.DNFService;

/**
 悬浮Manager
 */
public class FloatWindowManager {
    private volatile static FloatWindowManager mInstance;
    private WindowManager mWindowManager;
    private Context mContext;
    private WindowManager.LayoutParams mLayoutParams;
    private int layoutY;
    private int layoutX;
    private ValueAnimator animator;
    private FrameLayout mFrameLayout;


    public static synchronized FloatWindowManager getInstance() {
        if (mInstance == null) {
            synchronized (FloatWindowManager.class) {
                if (mInstance == null) {
                    mInstance = new FloatWindowManager();
                }
            }
        }
        return mInstance;
    }

    public FloatWindowManager initManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        showWindow(context);
        return this;
    }


    /**
     * 是否有悬浮框权限
     *
     * @return
     */
    public boolean requestPermission(Context context) {
        return SettingsCompat.canDrawOverlays(context, true, true);
    }

    /**
     * 加载 悬浮窗   没有内容
     */
    @SuppressLint("WrongConstant")
    private synchronized void showWindow(Context context) {

        mFrameLayout = new FrameLayout(context);
        View view = LayoutInflater.from(context).inflate(R.layout.window_layout, mFrameLayout);
        mLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        mLayoutParams.format = PixelFormat.RGBA_8888; //窗口透明
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP; //窗口位置
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        layoutY = displayMetrics.heightPixels / 2;
        layoutX = displayMetrics.widthPixels - mFrameLayout.getMeasuredWidth();
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.x = layoutX;
        mLayoutParams.y = layoutY;
        setListener();
    }

    public void showFloatWindow() {
        mWindowManager.addView(mFrameLayout, mLayoutParams);
    }

    /**
     * 设置 悬浮窗 view 滑动事件
     */
    @SuppressLint("ClickableViewAccessibility")
    private void setListener() {
        if (mFrameLayout != null) {

            mFrameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, HomeActivity.class));
                }
            });

            mFrameLayout.setOnTouchListener(new View.OnTouchListener() {
                private int moveX;   //动画平移距离
                int startX, startY;  //起始点
                boolean isMove;  //是否在移动
                long startTime;
                int finalMoveX;  //最后通过动画将mView的X轴坐标移动到finalMoveX
                boolean downMove = false;

                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = (int) event.getX();
                            startY = (int) event.getY();
                            startTime = System.currentTimeMillis();
                            isMove = false;
                            downMove = false;
                            return false;
                        case MotionEvent.ACTION_MOVE:
                            //当移动距离大于2时候，刷新界面。
                            if (Math.abs(startX - event.getX()) > 2 || Math.abs(startY - event.getY()) > 2) {
                                downMove = true;
                                mLayoutParams.x = (int) (event.getRawX() - startX);
                                mLayoutParams.y = (int) (event.getRawY() - startY);
                                updateViewLayout();   //更新mView 的位置
                            }
                            return true;
                        case MotionEvent.ACTION_UP:
                            long curTime = System.currentTimeMillis();
                            isMove = curTime - startTime > 100;
                            if (isMove) {
                                //判断mView是在Window中的位置，以中间为界
                                if (mLayoutParams.x + mFrameLayout.getMeasuredWidth() / 2 >= mWindowManager.getDefaultDisplay().getWidth() / 2) {
                                    finalMoveX = mWindowManager.getDefaultDisplay().getWidth() - mFrameLayout.getMeasuredWidth();
                                } else {
                                    finalMoveX = 0;

                                }
                                //使用动画移动mView
                                animator = ValueAnimator.ofInt(mLayoutParams.x, finalMoveX).setDuration(Math.abs(mLayoutParams.x - finalMoveX));
                                animator.addUpdateListener((ValueAnimator animation) -> {
                                    if (animation != null) {
                                        moveX = (int) animation.getAnimatedValue();
                                        mLayoutParams.x = (int) animation.getAnimatedValue();
                                        updateViewLayout();
                                    }
                                });
                                animator.start();
                            }

                            return isMove;
                    }
                    return false;
                }
            });
        }
    }


    /**
     * 刷新 circle view 位置
     */
    private void updateViewLayout() {
        if (null != mFrameLayout && null != mLayoutParams && mWindowManager != null) {
            try {
                mWindowManager.updateViewLayout(mFrameLayout, mLayoutParams);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        if (context != null) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
        return (int) dpValue;
    }

}
