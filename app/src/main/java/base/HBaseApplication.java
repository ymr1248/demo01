package base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Process;
import android.util.DisplayMetrics;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;


import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import adlibrary.utils.DisplayUtil;
import com.blankj.utilcode.util.Utils;

public final class HBaseApplication extends Application {

    public static Context context;
    public static Handler handler = new Handler();


    private static int level = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Utils.init(context);
//        EMOptions options = new EMOptions();
//        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//
//        //初始化
//        EMClient.getInstance().init(this, options);
//        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 百度地图全局context 的初始化
//        SDKInitializer.initialize(this);
//        //捕获全局异常
        exception.CrashHandler.getInstance().init();
//        PgyCrashManager.register(this);

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
////                .addInterceptor(new LoggerInterceptor("TAG"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                        //其他配置
//                .build();
//
//        OkHttpUtils.initClient(okHttpClient);


        //初始化push推送服务
//        if (shouldInit()) {
//            MiPushClient.registerPush(this, APP_ID, APP_KEY);
//        }
        //打开Log
//        LoggerInterface newLogger = new LoggerInterface() {
//
//            @Override
//            public void setTag(String tag) {
//                // ignore
//            }
//
//            @Override
//            public void log(String content, Throwable t) {
//                Log.d(TAG, content, t);
//            }
//
//            @Override
//            public void log(String content) {
//                Log.d(TAG, content);
//            }
//        };
//        Logger.setLogger(this, newLogger);


        initDisplayOpinion();

        Fresco.initialize(this);


        //
//        SugarContext.init(this);
    }





    private boolean shouldInit() {
//        if (!DeviceUtils.getManufacturer().equals("HUAWEI")) {
            ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
            List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
            String mainProcessName = getPackageName();
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo info : processInfos) {
                if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                    return true;
                }
            }
            return false;
        }
//        else{
//            return false;
//        }
//    }

    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static int getEmuiLeval() {
        // Finals 2016-6-14 如果获取过了就不用再获取了，因为读取配置文件很慢
        if (level > 0) {
            return level;
        }
        Properties properties = new Properties();
        File propFile = new File(Environment.getRootDirectory(), "build.prop");
        FileInputStream fis = null;
        if (propFile != null && propFile.exists()) {
            try {
                fis = new FileInputStream(propFile);
                properties.load(fis);
                fis.close();
                fis = null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        if (properties.containsKey("ro.build.hw_emui_api_level")) {
            String valueString = properties.getProperty("ro.build.hw_emui_api_level");
            try {
                level = Integer.parseInt(valueString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return level;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);//加上这句
    }
}



