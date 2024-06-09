package com.bingdou.dnfscript.tools;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 处理手势和按键的操作
 */
public class HandleControl {

    private static HandleControl instance;
    private List<String> eventList = new ArrayList<>();

    private Boolean isCanUse = false;

    public static HandleControl getInstance() {
        if (instance == null) {
            instance = new HandleControl();
        }
        return instance;
    }

    /**
     * 设置滑动点击长按反控操作是否可用
     *
     * @param isCanUse
     */
    public void setCanUse(boolean isCanUse) {
        this.isCanUse = isCanUse;
    }

    public boolean getCanUse() {
        return isCanUse;
    }
    /**
     * 处理滑动点击长按的事件
     *
     * @param msg 事件集合
     */
    public void sendPointerSync1(float x, float y) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(() -> {
            try {
                Instrumentation inst = new Instrumentation();
                inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_DOWN, x, y, 0));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

    }

    /**
     * 处理滑动点击长按的事件
     *
     * @param msg 事件集合
     */
    public void sendPointerSync(String msg) {
        try {
            Logger.d("HandleControl", "msg = " + msg);
            String substring = msg.substring(1, msg.length() - 1).replace(" ", "");
            Logger.d("HandleControl", "substring = " + substring);
            String[] msgs = substring.split(",");
            List<String> msgList = new ArrayList<String>(Arrays.asList(msgs));
            Logger.d("HandleControl", "msgList = " + msgList.toString());
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(() -> {
                try {
                    for (int i = 0; i < msgList.size(); i++) {
                        String msg1 = msgList.get(i);
                        int action;
                        float x;
                        float y;
                        String[] strs = msg1.split("a");
                        Logger.d("HandleControl", "strs = " + strs);
                        action = Integer.parseInt(strs[0]);
                        x = Float.parseFloat(strs[1]);
                        y = Float.parseFloat(strs[2]);
                        Logger.d("HandleControl", "action  = " + action + "; x  = " + x + "; y  = " + y);
                        Instrumentation inst = new Instrumentation();
                        inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), action, x, y, 0));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 收到消息后, 处理按键事件
     * @param keyCode 按键值
     */
    public void sendKeyDownUpSync(int keyCode) {
        Logger.d("HandleControl", "keyCode = " + keyCode);

        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(() -> {
                Logger.d("HandleControl", "keyCode  = " + keyCode);
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(keyCode);
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
