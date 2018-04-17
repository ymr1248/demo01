package com.yongxing.activity.demo01.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.utils.Constants;
import com.yongxing.activity.demo01.R;

import java.util.HashMap;

import HConstants.HConstants;
import Utils.ControlUtils;



public class LikeFragment extends Fragment implements View.OnClickListener {
    public static LikeFragment newInstance(String s) {
        LikeFragment homeFragment = new LikeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private TextView like_top;
    private ImageView like_right;
    private ImageView likeLeft;

    private LinearLayout likeChuange;
    private LinearLayout likeNetSet;
    private TextView fragmentTextView;
    private LinearLayout likeVideoSet;
    private LinearLayout likeTimeSet;
    private LinearLayout likeAlarmSet;
    private LinearLayout likeIpSet;
    private LinearLayout likeUpdate;
    private ImageView selectRight;


    private Context mContext;
    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;


    final String[] devices=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mlike, container, false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        like_top = (TextView) view.findViewById(R.id.fragment_text_view);
        like_right = (ImageView) view.findViewById(R.id.top_right);
        likeLeft = (ImageView) view.findViewById(R.id.top_left);
        likeLeft.setVisibility(View.INVISIBLE);
        likeChuange = (LinearLayout) view.findViewById(R.id.like_chuange);
        likeNetSet = (LinearLayout) view.findViewById(R.id.like_net_set);
        fragmentTextView = (TextView) view.findViewById(R.id.fragment_text_view);
        likeVideoSet = (LinearLayout) view.findViewById(R.id.like_video_set);
        likeTimeSet = (LinearLayout) view.findViewById(R.id.like_time_set);
        likeAlarmSet = (LinearLayout) view.findViewById(R.id.like_alarm_set);
//        likeIpSet = (LinearLayout) view.findViewById(R.id.like_ip_set);
        likeUpdate = (LinearLayout) view.findViewById(R.id.like_update);
        selectRight = view.findViewById(R.id.select_right_img);

        like_top.setText(s);
        like_right.setVisibility(View.INVISIBLE);
        likeChuange.setOnClickListener(this);
        likeAlarmSet.setOnClickListener(this);
        likeNetSet.setOnClickListener(this);
//        likeIpSet.setOnClickListener(this);
        likeTimeSet.setOnClickListener(this);
        likeVideoSet.setOnClickListener(this);
        likeUpdate.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //设备选择
            case R.id.like_chuange:
                final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
                alert = null;
                builder = new AlertDialog.Builder(getActivity());
                alert = builder
                        .setTitle("选择你喜欢的课程")
                        .setItems(lesson, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();

                alert.show();
                break;
            //网络设置
            case R.id.like_net_set:
                Toast.makeText(getActivity(), "网络设置", Toast.LENGTH_LONG).show();
                break;
            //录像设置
            case R.id.like_video_set:
                Toast.makeText(getActivity(), "录像设置", Toast.LENGTH_LONG).show();
                break;
            //时间设置
            case R.id.like_time_set:
                Toast.makeText(getActivity(), "时间设置", Toast.LENGTH_LONG).show();
                break;
          /*  //IP设置
            case R.id.like_ip_set:
                break;*/
            //报警设置
            case R.id.like_alarm_set:
                Toast.makeText(getActivity(), "报警设置", Toast.LENGTH_LONG).show();
                break;
            //设备更新
            case R.id.like_update:
                Toast.makeText(getActivity(), "设备更新", Toast.LENGTH_LONG).show();
                break;
        }

    }

    //获取设备信息
    private void getDevice() {

        HashMap<String, String> map = new HashMap<>();
        ControlUtils.getsEveryTime(HConstants.URL.AppAddMonitor, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                String json = resultBean.getJsonString();
                json = ControlUtils.chuangeStr(json);
            }

            @Override
            public void onFailure(String url) {

            }
        });
    }
}
