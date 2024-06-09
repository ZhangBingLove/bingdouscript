package com.bingdou.dnfscript.service;

import android.accessibilityservice.AccessibilityService;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.accessibility.AccessibilityEvent;

import com.bingdou.dnfscript.tools.AccessibilityHelper;
import com.bingdou.dnfscript.tools.Logger;

/**
 * Title:无障碍服务
 * <p>
 * description:
 * 这个服务是不需要你在activity里去开启的，属于系统级别辅助服务 需要在设置里去手动开启 和我们平常app里
 * 经常使用的service 是有很大不同的 非常特殊
 */
public class MyAccessibilityService extends AccessibilityService {

    public static final String TAG = "MyAccessibilityService";
    public static final String TEST="com.bingdou.dnfscript";

    public static MyAccessibilityService mService;

    private static int mCount = 0;

    /***
     * AccessibilityService 这个服务可以关联很多属性，这些属性 一般可以通过代码在这个方法里进行设置，
     * 我这里偷懒 把这些设置属性的流程用xml 写好 放在manifest里，如果你们要使用的时候需要区分版本号
     * 做兼容，在老的版本里是无法通过xml进行引用的 只能在这个方法里手写那些属性 一定要注意.
     * 同时你的业务如果很复杂比如需要初始化广播啊之类的工作 都可以在这个方法里写。
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Logger.d(TAG,"====建立服务链接====");

    }


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Logger.i("====启动Event====");
        //屏幕尺寸
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int x = displayMetrics.widthPixels;
        int y = displayMetrics.heightPixels;
        Logger.d(TAG,"======屏幕尺寸:x=" + x + "  y=" + y);

        mService = this;

        if (event.getPackageName() == null) {
            Logger.d(TAG,"=======event.getPackageName()为空====");
            return;
        }
        String packageName = event.getPackageName().toString();
        if (!TextUtils.isEmpty(packageName)) {
            switch (packageName) {
                case TEST://测试demo
                    Logger.d(TAG,"========测试demo=========");

//                    doTask();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onInterrupt() {
        Logger.d(TAG,"====无障碍服务要结束了====");
        mService = null;

    }

    /**
     * 服务是否启动
     **/
    public static boolean isStart() {
        return mService != null;
    }


    private void doTask() {
        //延时一秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger.d(TAG,"=========mCount=" + mCount);
        switch (mCount) {
            case 1://点击按钮
                //点击测试按钮
                AccessibilityHelper.getInstance().performClickById(mService, "com.kotlintest:id/mBtnTest", null);
                break;
            default:
                break;
        }
        mCount++;

    }

}