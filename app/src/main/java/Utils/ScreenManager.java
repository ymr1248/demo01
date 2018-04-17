package Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.lang.ref.WeakReference;


/**
 * Created by Administrator on 2017/8/8.
 */
public class ScreenManager {
    private static final String TAG = "ymr";
    private Context mContext;
    private static ScreenManager mSreenManager;
    // 使用弱引用，防止内存泄漏
    private WeakReference<Activity> mActivityRef;


    private ScreenManager(Context mContext){
        this.mContext = mContext;
    }


    // 单例模式
    public static ScreenManager getScreenManagerInstance(Context context){
        if(mSreenManager == null){
            synchronized (ScreenManager.class) {
                if (mSreenManager == null) {
                    mSreenManager = new ScreenManager(context);
                }
            }
        }
        return mSreenManager;
    }


    // 获得SinglePixelActivity的引用
    public void setSingleActivity(Activity mActivity){
        mActivityRef = new WeakReference<>(mActivity);
    }





    // 结束SinglePixelActivity
    public void finishActivity(){
        ScreenReceiverUtil sc=new ScreenReceiverUtil(mContext);
        if(mActivityRef != null){
            Log.i(TAG, "finishActivity: ");
            try {
                sc.stopScreenReceiverListener();
            }catch (Exception e){

            }
            Activity mActivity = mActivityRef.get();
            if(mActivity != null){
                mActivity.finish();
            }
        }
    }
}
