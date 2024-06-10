package com.bingdou.dnfscript.tools;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;

public class GestureHelper {

    private static final String TAG = "GestureHelper";

    private static final long GUSTURE_START_TIME = 0L; // 点击起始时间,单位毫秒
    private static final long GUSTURE_DURATION = 50L; //点击持续时间,单位毫秒

    private int mScreenWith = APPUtils.getWidth();
    private int mScreenHeight = APPUtils.getHeight();

    private static class Holder {
        private static GestureHelper instance = new GestureHelper();
    }

    public static GestureHelper getInstance() {
        return GestureHelper.Holder.instance;
    }

//    /***
//     * 设置屏幕尺寸
//     *
//     * @param currentScreenWidth 开发设备的实际屏幕宽度值
//     * @param currentScreenHeight 开发设备的实际屏幕高度值
//     * @return
//     */
//    public GestureHelper initSize(int currentScreenWidth, int currentScreenHeight) {
//
//    }

    /**
     * 手指竖直方向滑动
     **/
    public boolean verticalSlide(AccessibilityService service,
                                 int startY, int endY,
                                 long startTime, long duration) {

        return false;
    }

    /**
     * 手指竖直方向滑动
     **/
    public boolean verticalSlide(AccessibilityService service,
                                 int startY, int endY) {
        return false;
    }

    /**
     * 手指水平方向滑动
     **/
    public boolean horizontalSlide(AccessibilityService service,
                                   int startX, int endX,
                                   long startTime, long duration) {
        return false;
    }

    /**
     * 手指水平方向滑动
     **/
    public boolean horizontalSlide(AccessibilityService service,
                                   int startX, int endX) {
        return false;
    }

    /**
     * 手势点击
     **/
    public boolean clickByGesture(AccessibilityService service, int x, int y,
                                  long startTime, long duration) {
        return AccessibilityHelper.getInstance().performClickByGesture(service, x, y, startTime, duration);
    }

    /**
     * 手势点击
     **/
    public boolean clickByGesture(AccessibilityService service, int x, int y) {

        return clickByGesture(service, x, y, GUSTURE_START_TIME, GUSTURE_DURATION);
    }

}
