package com.bingdou.dnfscript.tools;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * 无障碍功能帮助类
 */
public class AccessibilityHelper {

    private AccessibilityHelper() {
    }

    private static class Holder {
        private static AccessibilityHelper instance = new AccessibilityHelper();
    }

    public static AccessibilityHelper getInstance() {
        return Holder.instance;
    }

    /**
     * 点击动作
     **/
    public boolean performClick(AccessibilityNodeInfo targetInfo) {
        return true;
    }

    /**
     * 点击返回键
     **/
    public boolean clickBackKey(AccessibilityService service) {
        return true;
    }

    /**
     * 点击Home键
     **/
    public boolean clickHomeKey(AccessibilityService service) {
        return true;
    }

    /**
     * 点击最近任务
     **/
    public boolean clickLastTaskKey(AccessibilityService service) {
        return true;
    }

    /**
     * 点击通知栏
     **/
    public boolean clickNotificationKey(AccessibilityService service) {
        return false;
    }

    /***
     * 根据控件显示内容text找到的控件是否存在
     *
     * 界面中可能出现多个控件显示同样的内容,则根据text获取的控件不止一个
     * 这时,则需要控件id做辅助筛选,当无viewId做筛选条件时,默认取找到第一个含内容的view返回
     *
     * @param service AccessibilityService对象
     * @param text 视图文字
     * @param viewId 参数是 com.xxx.xxx:id/tv_main,必须要带上包名
     *               当 viewId为null时,默认取找到第一个含内容的view作为查找的返回结果
     *
     * @return true:该控件存在    false:该控件不存在
     */
    public boolean isExistViewByText(AccessibilityService service, String text, String viewId) {
        return false;
    }

    /***
     * 根据控件ViewId找到的控件是否存在
     *
     * 当界面中是一个列表的时候,根据viewId查找可能会得到一个控件列表
     * 而所要寻找的不一定是默认的第一项,这时则需要文字即text辅助查找
     *
     * @param service AccessibilityService对象
     * @param viewId 参数是 com.xxx.xxx:id/tv_main,必须要带上包名
     * @param text 控件上显示的内容,当text为null时,默认取根据id获取到的列表的第一个
     *
     * @return true:该控件存在    false:该控件不存在
     */
    public boolean isExistViewById(AccessibilityService service, String viewId, String text) {
        return false;
    }

    /***
     * 根据 EditText中的内容找到 EditText 对象,并改变EditText中的内容
     *
     * @param service AccessibilityService对象
     * @param viewText EditText原来显示的内容
     * @param viewId 参数是 com.xxx.xxx:id/tv_main,必须要带上包名
     *               当 viewId为null时,默认取找到第一个含内容的view作为查找的返回结果
     * @param message  EditText中要设置的内容
     * @return
     */
    public boolean changeInputByViewText(AccessibilityService service, String viewText, String
            viewId, String message) {
        return false;
    }

    /***
     * 根据 EditText中的ViewId找到 EditText 对象,并改变EditText中的内容
     *
     * @param service  AccessibilityService对象
     * @param viewId 参数是 com.xxx.xxx:id/tv_main,必须要带上包名
     * @param viewText 控件上显示的内容,当text为null时,默认取根据id获取到的列表的第一个
     * @param message EditText中要设置的内容
     * @return
     */
    public boolean changeInputByViewId(AccessibilityService service, String viewId, String
            viewText, String message) {
        return false;
    }

    /***
     * 根据控件上显示的文字找到该控件,并执行点击事件
     *
     * 注：若该控件不可点击,则找其父控件甚至父级的父级...来执行点击
     *
     * 当界面中是一个列表的时候,根据viewId查找可能会得到一个控件列表
     * 而所啊哟寻找的不一定是默认的第一项,这时则需要文字即text辅助查找
     *
     * @param service AccessibilityService对象
     * @param text 控件上显示的内容
     * @param viewId 参数是 com.xxx.xxx:id/tv_main,必须要带上包名
     *               当 viewId为null时,默认取找到第一个含内容的view作为查找的返回结果
     * @return
     */
    public boolean performClickByText(AccessibilityService service, String text, String viewId) {
        return false;
    }

    /***
     * 根据控件ViewId找到该控件,并执行点击事件
     *
     * 注：若该控件不可点击,则找其父控件甚至父级的父级...来执行点击
     *
     * 当界面中是一个列表的时候,根据viewId查找可能会得到一个控件列表
     * 而所啊哟寻找的不一定是默认的第一项,这时则需要文字即text辅助查找
     *
     * @param service AccessibilityService对象
     * @param viewId 参数是 com.xxx.xxx:id/tv_main,必须要带上包名
     * @param text 控件上显示的内容,当text为null时,默认取根据id获取到的列表的第一个
     *
     * @return
     */
    public boolean performClickById(AccessibilityService service, String viewId, String text) {
        return false;
    }

    /***
     * 手势滑动
     *
     * 注: 开始滑动时间与滑动延长时间参考值如下：
     *     startTime=100L,
     *     duration=500L
     *
     * @param service  AccessibilityService对象
     * @param startX 起始 x 坐标
     * @param startY 起始 Y 坐标
     * @param endX  结束 X 坐标
     * @param endY  结束 Y 坐标
     * @param startTime  滑动开始时间(单位毫秒)
     * @param duration  滑动持续时间(单位毫秒)
     * @param callback 监听
     * @return
     */
    public boolean performGestureSliding(AccessibilityService service,
                                         float startX,
                                         float startY,
                                         float endX,
                                         float endY,
                                         long startTime,
                                         long duration,
                                         AccessibilityService.GestureResultCallback callback) {
        return false;
    }

    /***
     * 手势执行点击事件
     *
     * 注: 开始点击时间与点击延长时间参考值如下：
     *     startTime=50L,
     *     duration=500L
     *
     * @param service  AccessibilityService对象
     * @param x 点击屏幕的 x 坐标
     * @param y 点击屏幕的 y 坐标
     * @param startTime  滑动开始时间(单位毫秒)
     * @param duration  滑动持续时间(单位毫秒)
     * @param callback 监听
     * @return
     */
    public boolean performClickByGesture(AccessibilityService service,
                                         float x, float y,
                                         long startTime, long duration,
                                         AccessibilityService.GestureResultCallback callback) {
        return false;
    }

    /***
     * 线程休眠时间
     *
     * @param miao:double类型, 单位秒
     */
    public void waitTime(double miao) {

    }

}
