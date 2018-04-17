package com.yongxing.activity.demo01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.Bean.SpaceBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.HashMap;

import HConstants.HConstants;
import Utils.ControlUtils;

public class SpaceActivity extends Activity implements View.OnClickListener {

    private TextView spaceUsername;
    private TextView spaceCloudSize;
    private TextView spaceFreeTime;
    private ImageView topRight;
    private ImageView topLeft;
    private TextView topText;
    private String TAG = "YMR";
    private SeekBar seekBarSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        spaceUsername = (TextView) findViewById(R.id.space_username);
        spaceCloudSize = (TextView) findViewById(R.id.space_cloud_size);
        findViewById(R.id.join_membership).setOnClickListener(this);
        spaceFreeTime = (TextView) findViewById(R.id.space_free_time);
        seekBarSpace = (SeekBar) findViewById(R.id.seekBar_space);

        topLeft = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topText = (TextView) findViewById(R.id.fragment_text_view);

        topLeft.setVisibility(View.VISIBLE);
        topRight.setVisibility(View.INVISIBLE);
        topText.setText("云空间");
        topLeft.setOnClickListener(this);
//        getSpaceData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.join_membership:
                Intent intent = new Intent(this, JoinActivity.class);
                startActivity(intent);
                //TODO implement
                break;
            case R.id.top_left:
                finish();
                break;
        }
    }

    private void getSpaceData() {
        HashMap<String, String> map = new HashMap<>();

        map.put("userId", String.valueOf(ControlUtils.userBean.getId()));
        ControlUtils.getsEveryTime(HConstants.URL.AppGetSpace, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                String json = resultBean.getJsonString();
                json = ControlUtils.chuangeStr(json);

                SpaceBean spaceBean = ControlUtils.getEntityFromJson(json, SpaceBean.class);
                if (spaceBean == null) {
                    Toast.makeText(SpaceActivity.this,"还未开通会员",Toast.LENGTH_SHORT).show();
                    finish();
                } else {

                    spaceCloudSize.setText("空间大小：" + spaceBean.getUseSize() + "GB/" + spaceBean.getExtraSize() + "GB");
                    Long size1 = Long.parseLong(spaceBean.getUseSize());
                    Long size2 = Long.parseLong(spaceBean.getExtraSize());
                    int size3 = (int) (size1 * 100 / size2);
                    seekBarSpace.setProgress((int) size3);
                    spaceUsername.setText(spaceBean.getSpaceName());
                    Log.d(TAG, "spaceonSuccess: " + spaceBean.toString());
                }

            }

            @Override
            public void onFailure(String url) {

            }
        });

    }
}
