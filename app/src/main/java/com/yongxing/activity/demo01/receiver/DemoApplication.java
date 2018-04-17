package com.yongxing.activity.demo01.receiver;


import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.yongxing.activity.demo01.activity.LoginActivity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class DemoApplication extends Application {

    public static final String APP_ID = "2882303761517725334";
    public static final String APP_KEY = "5101772576334";
    public static final String TAG = "delman";
    private static DemoHandler sHandler = null;
    private static LoginActivity sMainActivity = null;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: @@@@@@@@@@@@@@@@@@@@@@@");
        //初始化push推送服务
        if(shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
//                Process.myPid();
         /*myPid &&*/
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid ==myPid&& mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
    public static DemoHandler getHandler() {
        return sHandler;
    }

    public static void setMainActivity(LoginActivity activity) {
        sMainActivity = activity;
    }

    public static class DemoHandler extends Handler {

        private Context context;

        public DemoHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            String s = (String) msg.obj;
            if (sMainActivity != null) {
//                sMainActivity.refreshLogInfo();
            }
            if (!TextUtils.isEmpty(s)) {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        }
    }
}