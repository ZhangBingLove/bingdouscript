package com.bingdou.dnfscript.tools;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class APPUtils {
    public static final String TAG = "APPUtils";

    private static Activity mActivity;

    /**
     * DNF包名 com.tencent.tmgp.dnf/.DNFMainActivity
     * <p>
     * com.tzy.mhxzx.new0/com.pwrd.tzyxmznew.UnityPlayerActivity
     */
    public static final String DNF_PACKAGE_NAME = "com.tencent.tmgp.dnf";
//    public static final String DNF_PACKAGE_NAME = "com.tencent.gamehelper.dnf";
    /**
     * DNF类名
     */
    public static final String DNF_CLASS_NAME = "com.tencent.tmgp.dnf.DNFMainActivity";
//    public static final String DNF_CLASS_NAME = "com.tencent.gamehelper.ui.main.WelcomeActivity";

    /**
     * 打开第三方APP
     */
    public static void startApp(Context context, String packageName, String className) {

        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.tencent.mobileqq");
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);


//        try {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            ComponentName cmp = new ComponentName(packageName, className);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setComponent(cmp);
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            // TODO: handle exception
//            Toast.makeText(context, "检查到您手机没有DNF手游，请安装后使用该功能", Toast.LENGTH_SHORT).show();
//        }

//        try {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setComponent(cmp);
//            context.startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            // TODO: handle exception
//            Toast.makeText(context,"检查到您手机没有DNF手游，请安装后使用该功能", Toast.LENGTH_SHORT).show();
//        }

    }

    public static void openApp(Activity activity, String packageName) {
        PackageInfo pi;
        try {
            pi = activity.getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.setPackage(pi.packageName);
            PackageManager pManager = activity.getPackageManager();
            List<ResolveInfo> apps = pManager.queryIntentActivities(
                    resolveIntent, 0);
            for (ResolveInfo app : apps) {

                Log.d(TAG, String.format("%s %s", app.activityInfo.packageName, app.activityInfo.name));
                packageName = app.activityInfo.packageName;
                String className = app.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                try {
                    activity.startActivity(intent);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
      /*  ResolveInfo ri = (ResolveInfo) apps.iterator().next();
        if (ri != null) {
            packageName = ri.activityInfo.packageName;
            String className = ri.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            activity.startActivity(intent);
        }*/
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package Name not found: " + e.getMessage() + ", App is not installed.");
        } catch (SecurityException e) {
            Log.e(TAG, "SecurityException");
        }
    }

    private boolean startAccessibilitySettings(Context context) {
        // 判断无障碍功能是否已经开启
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        // 如果无障碍功能未开启，则跳转到无障碍功能设置页面
        if (accessibilityEnabled != 1) {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity(intent);
        } else {
            return true;
        }
        return false;
    }

    /**
     * 判断应用是否已经运行
     */
    public static void appIsRuning() {

    }

    public static int getWidth() {
        if (mActivity != null) {
            return mActivity.getWindowManager().getDefaultDisplay().getWidth();
        }
        return 0;
    }

    public static int getHeight() {
        if (mActivity != null) {
            return mActivity.getWindowManager().getDefaultDisplay().getHeight();
        }
        return 0;
    }

}
