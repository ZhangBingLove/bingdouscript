package com.bingdou.dnfscript.service;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.activity.HomeActivity;
import com.bingdou.dnfscript.activity.ScreenCaptureActivity;
import com.bingdou.dnfscript.activity.TestActivity;
import com.bingdou.dnfscript.activity.TestRecognitionActivity;
import com.bingdou.dnfscript.tools.Logger;

public class DNFService extends Service {

    private static final String TAG = "DNFService";

    public DNFService() {

    }

    //重写onBind方法
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //重写onCreate方法，用于服务启动时创建通知，并设置为前台通知
    @SuppressLint("ForegroundServiceType")
    @Override
    public void onCreate() {
        Logger.d(TAG, "onCreate()");

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        //创建NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("NOTIFICATION_ID",
                    "NOTIFICATION_NAME", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        startForeground(1, getNotification());    //启动前台通知
        Intent intent = new Intent(this, ScreenCaptureActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        super.onCreate();
    }

    //重写onStartCommand方法，用于执行具体的网络请求事项
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    //重写onDestroy，在APP退出时停止前台服务。
    @Override
    public void onDestroy() {
        Logger.d(TAG, "onDestroy()");
        stopForeground(true);
        super.onDestroy();
    }

    //这个必须加，不能设置为null，设置通知具体显示内容
    private Notification getNotification() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)  //drawable资源中的图标文件
                .setContentTitle("测试服务")
                .setContentText("我正在运行");//标题和内容可以不加
        //设置Notification的ChannelID,否则不能正常显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("NOTIFICATION_ID");
        }
        Notification notification = builder.build();
        return notification;
    }
}
