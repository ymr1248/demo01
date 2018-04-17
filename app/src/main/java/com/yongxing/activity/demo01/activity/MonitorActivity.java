package com.yongxing.activity.demo01.activity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AVAPI.Client;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.tutk.IOTC.AVAPIs;
import com.tutk.IOTC.IOTCAPIs;
import com.yongxing.activity.demo01.Bean.MonitorBean;
import com.yongxing.activity.demo01.Bean.MonitorListBean;
import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.view.DeleteRecyclerView;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.MediaController;
import com.yongxing.activity.demo01.utils.MyAdapter;
import com.yongxing.activity.demo01.utils.OnItemClickListener;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HConstants.HConstants;
import Utils.ControlUtils;
import Utils.SerializableMap;

public class MonitorActivity extends Activity implements View.OnClickListener {

    private DeleteRecyclerView monitorList;
    private TextView topTitle;
    private ImageView topBack;
    private ImageView topRight;
    private Button monitorDelete;
    private Button monitorArming;
    private Button monitorEdit;
    private Button monitorScreenshot;
    private MediaPlayer mPlayer = null;
    private SurfaceHolder surfaceHolder;

    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    java.util.List<Map<String, Object>> datas = new ArrayList<>();
    List<String> groupkey = new ArrayList<>();
    private ArrayList<String> mList;


    private static final String TAG = "YMR";
    public static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String DEFAULT_CACHE_DIR = SDCARD_DIR + "/PLDroidPlayer";


    private PLVideoTextureView mVideoView;
    private Toast mToast = null;
    private int mRotation = 0;
    private int mDisplayAspectRatio = PLVideoTextureView.ASPECT_RATIO_FIT_PARENT; //default
    private TextView mStatInfoTextView;
    private boolean mIsLiveStreaming;
    String videoPath;
    Map<String, Object> mapinfo;
    private String uid;
    private int sid;
    private int avIndex = -1;
    static final String UID = "CPVTXMWUBRJLMUPK111A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        Bundle bundle = getIntent().getExtras();
        SerializableMap serializableMap = (SerializableMap) bundle
                .get("monitorinfo");
        mapinfo = serializableMap.getMap();

        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        mIsLiveStreaming = true;
        videoPath = "rtmp://192.168.0.109/live/abc";
        mVideoView = findViewById(R.id.VideoView);
        View loadingView = findViewById(R.id.LoadingView);
        loadingView.setVisibility(View.VISIBLE);
        mVideoView.setBufferingIndicator(loadingView);
        View coverView = findViewById(R.id.CoverView);
        mVideoView.setCoverView(coverView);
        mStatInfoTextView = findViewById(R.id.StatInfoTextView);


        int codec = AVOptions.MEDIA_CODEC_HW_DECODE;
        AVOptions options = new AVOptions();
        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, mIsLiveStreaming ? 1 : 0);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        options.setInteger(AVOptions.KEY_MEDIACODEC, codec);
        boolean disableLog = false;
        options.setInteger(AVOptions.KEY_LOG_LEVEL, disableLog ? 5 : 0);
        boolean cache = false;
        if (!mIsLiveStreaming && cache) {
            options.setString(AVOptions.KEY_CACHE_DIR, DEFAULT_CACHE_DIR);
        }
        mVideoView.setAVOptions(options);
        mVideoView.setDebugLoggingEnabled(!disableLog);

        // You can mirror the display
        // mVideoView.setMirror(true);

        // You can also use a custom `MediaController` widget
        MediaController mediaController = new MediaController(this, !mIsLiveStreaming, mIsLiveStreaming);
        mediaController.setOnClickSpeedAdjustListener(mOnClickSpeedAdjustListener);
        mVideoView.setMediaController(mediaController);

        mVideoView.setOnInfoListener(mOnInfoListener);
        mVideoView.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
        mVideoView.setOnBufferingUpdateListener(mOnBufferingUpdateListener);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnErrorListener(mOnErrorListener);

        mVideoView.setLooping(false);

//        mVideoView.setVideoPath(videoPath);
//        mVideoView.start();

       /* AVOptions.MEDIA_CODEC_HW_DECODE;*/
        topBack = (ImageView) findViewById(R.id.top_left);
        topTitle = (TextView) findViewById(R.id.fragment_text_view);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setVisibility(View.INVISIBLE);
        topBack.setVisibility(View.VISIBLE);
        topTitle.setText("摄像头名称");
//        bindViews();
        monitorScreenshot = (Button) findViewById(R.id.monitor_btn1);
        monitorArming = (Button) findViewById(R.id.monitor_btn2);
        monitorEdit = (Button) findViewById(R.id.monitor_btn3);
        monitorDelete = (Button) findViewById(R.id.monitor_btn4);

        topBack.setOnClickListener(this);
        monitorScreenshot.setOnClickListener(this);
        monitorArming.setOnClickListener(this);
        monitorEdit.setOnClickListener(this);
        monitorDelete.setOnClickListener(this);
        datas = getData();
        monitorList = (DeleteRecyclerView) findViewById(R.id.monitor_list);
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(i + "");
        }
        final MyAdapter adapter = new MyAdapter(this, mList);
        monitorList.setLayoutManager(new LinearLayoutManager(this));
        monitorList.setAdapter(adapter);
        monitorList.getItemAnimator().setChangeDuration(0);
        monitorList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MonitorActivity.this, "** " + datas.get(position) + " **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                adapter.removeItem(position);
            }
        });
        getDatas();

        //初始化
//        mVideoView = (PLVideoTextureView) findViewById(R.id.VideoView);
        /*//设置加载动画
        mLoadingView = (ProgressBar) findViewById(R.id.LoadingView);
        mVideoView.setBufferingIndicator(mLoadingView);
//音量和亮度初始化
        mVolumeBrightnessLayout = findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView) findViewById(R.id.operation_bg);
        mOperationPercent = (ImageView) findViewById(R.id.operation_percent);

       //获取最大音量
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
*/

    }


    private void getDatas() {
        HashMap<String, String> map = new HashMap<>();

        map.put("id", String.valueOf(mapinfo.get("id")));
        ControlUtils.getsEveryTime(HConstants.URL.AppMonitor, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {

                String json = resultBean.getJsonString();
                json = ControlUtils.chuangeStr(json);
                MonitorBean monitorBean = ControlUtils.getEntityFromJson(json, MonitorBean.class);
                Log.d(TAG, "maponSuccess: " + monitorBean.toString());
                Log.d(TAG, "maponSuccess: " + monitorBean.getMonitorName());

            }

            @Override
            public void onFailure(String url) {

            }
        });
    }

    private void DeleteMonitor() {
        HashMap<String, String> map = new HashMap<>();

        map.put("id", String.valueOf(mapinfo.get("id")));
        ControlUtils.getsEveryTime(HConstants.URL.AppDeleteMonitor, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                if (resultBean.getResource() == 1) {

                    finish();
                } else {
                    Toast.makeText(MonitorActivity.this, "删除失败！", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(String url) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.monitor_btn1:
//                mPlayer.start();
                //TODO implement
                (new Thread() {
                    public void run() {
                        Client.start(UID);
                    }
                }).start();


                break;
            case R.id.monitor_btn2:
                //TODO implement
init();
//                mPlayer.pause();
                break;
            case R.id.monitor_btn3:
                //TODO implement
//                mPlayer.stop();
                break;
            case R.id.monitor_btn4:
                alert = null;
                builder = new AlertDialog.Builder(this);
                alert = builder
                        .setMessage("设备组操作?")
                       /* .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MonitorActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })*/
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                Toast.makeText(MonitorActivity.this, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MonitorActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).create();             //创建AlertDialog对象
                alert.show();                    //显示对话框
                break;
            case R.id.top_left:
                //TODO implement
                finish();
                break;
        }
    }

    public void init() {
        int ret = IOTCAPIs.IOTC_Initialize2(0);
        if (ret != IOTCAPIs.IOTC_ER_NoERROR) {

            System.out.println("IOTCAPIs_Device exit…!!\\n"+ret);
            Toast.makeText(this, "IOTCAPIs_Device exit…!!\\n", Toast.LENGTH_LONG).show();
            return;
        }

        AVAPIs.avInitialize(3);
        sid = IOTCAPIs.IOTC_Get_SessionID();
        if (sid < 0) {
            System.out.println("IOTC_Get_SessionID error code [%d]\\n" + sid);
            Toast.makeText(this, "IOTC_Get_SessionID error code [%d]\\n" + sid, Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void connet(String uid) {
        this.uid = uid;
        sid=0;
        int a = IOTCAPIs.IOTC_Connect_ByUID_Parallel(uid, sid);
        System.out.printf("IOTC_Connect_ByUID_Parallel[%d]\n", a);
        int[] srvType = new int[1];
        String username = "admin";
        String password = "admin123";
        System.out.printf(username + ":" + password + "\n");
        avIndex = AVAPIs.avClientStart(sid, username, password, 20000, srvType, 0);
        if (avIndex < 0) {
            System.out.printf("avClientStart failed[%d]\n", avIndex);
            Toast.makeText(this, "avClientStart failed[%d]\n", Toast.LENGTH_SHORT).show();
            return;
        } else {
            System.out.printf("avClientStart connet\n", avIndex);
            Toast.makeText(this, "avClientStart connet\n", Toast.LENGTH_SHORT).show();
        }
    }

    public void coles() {
        AVAPIs.avClientStop(avIndex);
        System.out.printf("avClientStop OK\n");
        IOTCAPIs.IOTC_Session_Close(sid);
        System.out.printf("IOTC_Session_Close OK\n");
        AVAPIs.avDeInitialize();
        IOTCAPIs.IOTC_DeInitialize();
        System.out.printf("StreamClient exit...\n");
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        groupkey.add("A");
        groupkey.add("B");
        Map<String, Object> map = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("title", "A");
        map.put("info", "连接成功");
        map.put("show", true);
        map.put("group", "1A");
        list.add(map);
        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, Object>();
            map.put("title", "A组--00" + i);
            map.put("info", "连接成功");
            map.put("show", true);
            map.put("group", "A");

            list.add(map);
        }

        map = new HashMap<String, Object>();
        map.put("title", "B");
        map.put("info", "连接成功");
        map.put("show", true);
        map.put("group", "1B");
        list.add(map);
        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, Object>();
            map.put("title", "B组--00" + i);
            map.put("info", "连接成功");
            map.put("show", true);
            map.put("group", "B");
            list.add(map);
        }

        return list;
    }


    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
        mToast = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }

    public void onClickRotate(View v) {
        mRotation = (mRotation + 90) % 360;
        mVideoView.setDisplayOrientation(mRotation);
    }

    public void onClickSwitchScreen(View v) {
        mDisplayAspectRatio = (mDisplayAspectRatio + 1) % 5;
        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);
        switch (mVideoView.getDisplayAspectRatio()) {
            case PLVideoTextureView.ASPECT_RATIO_ORIGIN:
                showToastTips("Origin mode");
                break;
            case PLVideoTextureView.ASPECT_RATIO_FIT_PARENT:
                showToastTips("Fit parent !");
                break;
            case PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT:
                showToastTips("Paved parent !");
                break;
            case PLVideoTextureView.ASPECT_RATIO_16_9:
                showToastTips("16 : 9 !");
                break;
            case PLVideoTextureView.ASPECT_RATIO_4_3:
                showToastTips("4 : 3 !");
                break;
            default:
                break;
        }
    }

    private PLMediaPlayer.OnInfoListener mOnInfoListener = new PLMediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
            Log.i(TAG, "OnInfo, what = " + what + ", extra = " + extra);
            switch (what) {
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_START:
                    break;
                case PLMediaPlayer.MEDIA_INFO_BUFFERING_END:
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    showToastTips("First video render time: " + extra + "ms");
                    break;
                case PLMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                    Log.i(TAG, "First audio render time: " + extra + "ms");
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_FRAME_RENDERING:
                    Log.i(TAG, "video frame rendering, ts = " + extra);
                    break;
                case PLMediaPlayer.MEDIA_INFO_AUDIO_FRAME_RENDERING:
                    Log.i(TAG, "audio frame rendering, ts = " + extra);
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_GOP_TIME:
                    Log.i(TAG, "Gop Time: " + extra);
                    break;
                case PLMediaPlayer.MEDIA_INFO_SWITCHING_SW_DECODE:
                    Log.i(TAG, "Hardware decoding failure, switching software decoding!");
                    break;
                case PLMediaPlayer.MEDIA_INFO_METADATA:
                    Log.i(TAG, mVideoView.getMetadata().toString());
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_BITRATE:
                case PLMediaPlayer.MEDIA_INFO_VIDEO_FPS:
                    updateStatInfo();
                    break;
                case PLMediaPlayer.MEDIA_INFO_CONNECTED:
                    Log.i(TAG, "Connected !");
                    break;
                case PLMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                    Log.i(TAG, "Rotation changed: " + extra);
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer mp, int errorCode) {
            Log.e(TAG, "Error happened, errorCode = " + errorCode);
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    /**
                     * SDK will do reconnecting automatically
                     */
                    showToastTips("IO Error !");
                    return false;
                case PLMediaPlayer.ERROR_CODE_OPEN_FAILED:
                    showToastTips("failed to open player !");
                    break;
                case PLMediaPlayer.ERROR_CODE_SEEK_FAILED:
                    showToastTips("failed to seek !");
                    break;
                default:
                    showToastTips("unknown error !");
                    break;
            }
            finish();
            return true;
        }
    };

    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer) {
            Log.i(TAG, "Play Completed !");
            showToastTips("Play Completed !");
            finish();
        }
    };

    private PLMediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener = new PLMediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int precent) {
            Log.i(TAG, "onBufferingUpdate: " + precent);
        }
    };

    private PLMediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new PLMediaPlayer.OnVideoSizeChangedListener() {
        @Override
        public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int width, int height) {
            Log.i(TAG, "onVideoSizeChanged: width = " + width + ", height = " + height);
        }
    };

    private MediaController.OnClickSpeedAdjustListener mOnClickSpeedAdjustListener = new MediaController.OnClickSpeedAdjustListener() {
        @Override
        public void onClickNormal() {
            // 0x0001/0x0001 = 2
            mVideoView.setPlaySpeed(0X00010001);
        }

        @Override
        public void onClickFaster() {
            // 0x0002/0x0001 = 2
            mVideoView.setPlaySpeed(0X00020001);
        }

        @Override
        public void onClickSlower() {
            // 0x0001/0x0002 = 0.5
            mVideoView.setPlaySpeed(0X00010002);
        }
    };

    private void showToastTips(final String tips) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(MonitorActivity.this, tips, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    private void updateStatInfo() {
        long bitrate = mVideoView.getVideoBitrate() / 1024;
        final String stat = bitrate + "kbps, " + mVideoView.getVideoFps() + "fps";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mStatInfoTextView.setText(stat);
            }
        });
    }





   /* private void bindViews() {
        sfv_show = (SurfaceView) findViewById(R.id.sfv_show);

        //初始化SurfaceHolder类，SurfaceView的控制器
        surfaceHolder = sfv_show.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFixedSize(320, 220);   //显示的分辨率,不设置为视频默认

    }
*/
  /*  @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Uri uri= Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
        mPlayer = MediaPlayer.create(this, uri);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setDisplay(surfaceHolder);    //设置显示视频显示在SurfaceView上
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            mPlayer.stop();
        }
        mPlayer.release();
    }*/


}
