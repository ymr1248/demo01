package Utils;

import android.content.Context;
import android.util.Log;



import java.util.HashMap;


/**
 * Created by lenovo on 2017/8/15.
 */

public class HuaweiUtil {

    private Context mContext;

    private static HuaweiUtil instance;// 单例模式


    public static final String TAG = "HuaweiIdActivity";




    public HuaweiUtil(Context context) {
        this.mContext = context;
//        initHuaWeiPush();
    }


    public static HuaweiUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (HuaweiUtil.class){
                if (instance == null){
                    instance = new HuaweiUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 使用同步接口来获取pushtoken
     * 结果通过广播的方式发送给应用，不通过标准接口的pendingResul返回
     * CP可以自行处理获取到token
     * 同步获取token和异步获取token的方法CP只要根据自身需要选取一种方式即可
     *//*
    private void getTokenSync() {
        if(!client.isConnected()) {
            Log.i(TAG, "获取token失败，原因：HuaweiApiClient未连接");
            client.connect();
            return;
        }

        //需要在子线程中调用函数
        new Thread() {

            public void run() {
                Log.i(TAG, "同步接口获取push token");
                PendingResult<TokenResult> tokenResult = HuaweiPush.HuaweiPushApi.getToken(client);
                TokenResult result = tokenResult.await();
                if(result.getTokenRes().getRetCode() == 0) {
                    //当返回值为0的时候表明获取token结果调用成功
                    Log.i(TAG, "获取push token 成功，等待广播");
                }
            };
        }.start();
    }

    private void initHuaWeiPush(){
        client = new HuaweiApiClient.Builder(mContext)
                .addApi(HuaweiPush.PUSH_API)
                .addConnectionCallbacks(new HuaweiApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected() {
                        //华为移动服务client连接成功，在这边处理业务自己的事件
                        Log.i(TAG, "HuaweiApiClient 连接成功");
                        getTokenAsyn();
                    }

                    @Override
                    public void onConnectionSuspended(int cause) {
                        Log.i(TAG, "HuaweiApiClient 连接异常");
                        client.connect();
                    }
                })
                .addOnConnectionFailedListener(new HuaweiApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult arg0) {
                        Log.i(TAG, "HuaweiApiClient连接失败，错误码：" + arg0.getErrorCode());
                        if(HuaweiApiAvailability.getInstance().isUserResolvableError(arg0.getErrorCode())) {
                            final int errorCode = arg0.getErrorCode();

                        } else {
                            //其他错误码请参见开发指南或者API文档
                        }
                    }
                })
                .build();

        //建议在oncreate的时候连接华为移动服务
        //业务可以根据自己业务的形态来确定client的连接和断开的时机，但是确保connect和disconnect必须成对出现
        client.connect();
    }

    *//**
     * 使用异步接口来获取pushtoken
     * 结果通过广播的方式发送给应用，不通过标准接口的pendingResul返回
     * CP可以自行处理获取到token
     * 同步获取token和异步获取token的方法CP只要根据自身需要选取一种方式即可
     *//*
    private void getTokenAsyn() {
        if(!client.isConnected()) {
            Log.i(TAG, "获取token失败，原因：HuaweiApiClient未连接");
            client.connect();
            return;
        }

        Log.i(TAG, "异步接口获取push token");
        PendingResult<TokenResult> tokenResult = HuaweiPush.HuaweiPushApi.getToken(client);
        tokenResult.setResultCallback(new ResultCallback<TokenResult>() {

            @Override
            public void onResult(TokenResult result) {
                //这边的结果只表明接口调用成功，是否能收到响应结果只在广播中接收
                String token =result.getTokenRes().getToken();
                DButils.put(HConstants.KEY.RegId, token);
                String userCode = DButils.get(HConstants.KEY.userCode);
                setUpDataRegId(userCode, token);
            }
        });
    }

    *//**
     * 异步方式获取PUSH的连接状态
     * 结果会通过通知发送出来
     *//*
    private void getPushStatus() {
        if(!client.isConnected()) {
            Log.i(TAG, "获取PUSH连接状态失败，原因：HuaweiApiClient未连接");
            client.connect();
            return;
        }

        //需要在子线程中调用函数
        new Thread() {
            public void run() {
                Log.i(TAG, "开始获取PUSH连接状态");
                HuaweiPush.HuaweiPushApi.getPushState(client);
                // 状态结果通过广播返回
            };
        }.start();
    }

    *//**
     * 设置是否允许应用接收PUSH透传消息
     * 若不调用该方法则默认为开启
     * 在开发者网站上发送push消息分为通知和透传消息
     * 通知为直接在通知栏收到通知，通过点击可以打开网页，应用 或者富媒体，不会收到onPushMsg消息
     * 透传消息不会展示在通知栏，应用会收到onPushMsg
     * 此开关只对透传消息有效
     * @param flag true 允许  false 不允许
     *//*
    private void setReceiveNormalMsg(boolean flag) {
        if(!client.isConnected()) {
            Log.i(TAG, "设置是否接收push消息失败，原因：HuaweiApiClient未连接");
            client.connect();
            return;
        }
        if(flag == true) {
            Log.i(TAG, "允许应用接收push消息");
        } else {
            Log.i(TAG, "禁止应用接收push消息");
        }
        HuaweiPush.HuaweiPushApi.enableReceiveNormalMsg(client, flag);
    }

    *//**
     * 应用删除通过getToken接口获取到的token
     * 应用调用注销token接口成功后，客户端就不会再接收到PUSH消息
     * CP应该在调用该方法后，自行处理本地保存的通过gettoken接口获取到的TOKEN
     *//*
    private void deleteToken() {

        if(!client.isConnected()) {
            Log.i(TAG, "注销token失败，原因：HuaweiApiClient未连接");
            client.connect();
            return;
        }

        //需要在子线程中执行删除token操作
        new Thread() {
            @Override
            public void run() {
                //调用删除token需要传入通过getToken接口获取到token，并且需要对token进行非空判断
            }
        }.start();
    }


    private void setUpDataRegId(String userCode, String mRegId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userCode", userCode);
        map.put("regId", mRegId);
        ControlUtils.getsEveryTime(HConstants.URL.updateRegId, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                Log.d("Helen", result);
            }

            @Override
            public void onFailure(String url) {
                // Log.d("Helen", "更新推送ID失败");
            }
        });

    }*/
}
