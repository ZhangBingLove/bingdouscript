package com.bingdou.dnfscript.tools;


import android.text.TextUtils;
import android.util.Log;


public class Logger {
    public static final String defaultTag = "BingDou";

    private boolean mLoggerDebug = true;

    public static void d(String msg) {
        Log.d(defaultTag, head() + msg);
    }

    public static void d(String tag, String msg) {
        String t = tag;
        if (TextUtils.isEmpty(t)) {
            t = defaultTag;
        }
        Log.d(t, head() + msg);
    }

    public static void i(String msg) {
        Log.i(defaultTag, head() + msg);
    }

    public static void i(String tag, String msg) {
        String t = tag;
        if (TextUtils.isEmpty(t)) {
            t = defaultTag;
        }
        Log.i(t, head() + msg);
    }

    public static void i(String tag, String msg, boolean releaseShow) {
            String t = tag;
            if (TextUtils.isEmpty(t)) {
                t = defaultTag;
            }
            Log.i(t, head() + msg);
    }

    public static void e(String msg) {
        Log.e(defaultTag, head() + msg);
    }

    public static void e(String tag, String msg) {
        String t = tag;
        if (TextUtils.isEmpty(t)) {
            t = defaultTag;
        }
        Log.e(t, head() + msg);
    }

    private static String head() {
        Thread thread = Thread.currentThread();
        StackTraceElement[] stackTrace = thread.getStackTrace();
        int index = 4;
        String className = stackTrace[index].getClassName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        String stringBuilder = "[thread:" +
                thread.getName() +
                "]{" +
                className +
                "#" +
                methodName +
                ":" +
                lineNumber +
                "} ";
        return stringBuilder;
    }
}
