package com.yongxing.activity.demo01.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.Bean.SpaceBean;
import com.yongxing.activity.demo01.activity.AboutActivity;
import com.yongxing.activity.demo01.activity.OperateActivity;
import com.yongxing.activity.demo01.activity.PassSettingActivity;
import com.yongxing.activity.demo01.activity.PushActivity;
import com.yongxing.activity.demo01.activity.SpaceActivity;
import com.yongxing.activity.demo01.activity.UserMessageActivity;
import com.yongxing.activity.demo01.utils.Constants;
import com.yongxing.activity.demo01.R;

import java.util.HashMap;

import HConstants.HConstants;
import Utils.ControlUtils;




public class PersonFragment extends Fragment implements View.OnClickListener {
    public static PersonFragment newInstance(String s) {
        PersonFragment homeFragment = new PersonFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private ImageView persionLeft;
    private ImageView persionShare;
    private RoundedImageView persionImage;
    private TextView persionName;
    private TextView persionSign;
    private LinearLayout persionPush;
    private TextView fragmentTextView;
    private LinearLayout persionPhoto;

    private LinearLayout persionOperate;
    private LinearLayout persionPassSet;
    private LinearLayout persionAbout;
    private LinearLayout persionQuit;
    private LinearLayout persionSpaceCloud;
    private LinearLayout persionInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mperson, container, false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        persionImage = (RoundedImageView) view.findViewById(R.id.persion_image);
        persionName = (TextView) view.findViewById(R.id.persion_name);
        persionSign = (TextView) view.findViewById(R.id.persion_sign);
        persionPush = (LinearLayout) view.findViewById(R.id.persion_push);
        fragmentTextView = (TextView) view.findViewById(R.id.fragment_text_view);
        persionPhoto = (LinearLayout) view.findViewById(R.id.persion_photo);
        persionInfo = (LinearLayout) view.findViewById(R.id.persion_info);
        persionOperate = (LinearLayout) view.findViewById(R.id.persion_operate);
        persionPassSet = (LinearLayout) view.findViewById(R.id.persion_pass_set);
        persionAbout = (LinearLayout) view.findViewById(R.id.persion_about);
        persionQuit = (LinearLayout) view.findViewById(R.id.persion_quit);
        persionSpaceCloud = (LinearLayout) view.findViewById(R.id.space_layout);
        persionShare = (ImageView) view.findViewById(R.id.top_right);
        persionLeft = (ImageView) view.findViewById(R.id.top_left);
        persionLeft.setVisibility(View.INVISIBLE);
        persionSpaceCloud.setOnClickListener(this);
        persionShare.setOnClickListener(this);
        persionImage.setOnClickListener(this);
        persionQuit.setOnClickListener(this);
        persionAbout.setOnClickListener(this);
        persionInfo.setOnClickListener(this);
        persionOperate.setOnClickListener(this);
        persionPassSet.setOnClickListener(this);
        persionPush.setOnClickListener(this);
        persionPhoto.setOnClickListener(this);
        fragmentTextView.setText(s);
        persionName.setText(ControlUtils.userBean.getUserName());
        if (ControlUtils.userBean.getAvatar()!=null&&!ControlUtils.userBean.getAvatar().equals("")){
            persionImage.setImageURI(Uri.parse(ControlUtils.userBean.getAvatar()));
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击云空间
            case R.id.space_layout:
                Intent intent_space = new Intent(getActivity(), SpaceActivity.class);
                getActivity().startActivity(intent_space);

                break;
            case R.id.persion_info:
                Intent p_info = new Intent(getActivity(), UserMessageActivity.class);
                getActivity().startActivity(p_info);


                break;
            //点击头像
            case R.id.persion_image:
                Intent p_image = new Intent(getActivity(), UserMessageActivity.class);
                getActivity().startActivity(p_image);
                break;
            //推送通知
            case R.id.persion_push:
                Intent p_push = new Intent(getActivity(), PushActivity.class);
                getActivity().startActivity(p_push);
                break;
            //相册
            case R.id.persion_photo:
              /*  Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);*/
                break;

            //操作指导
            case R.id.persion_operate:
                Intent p_operate = new Intent(getActivity(), OperateActivity.class);
                getActivity().startActivity(p_operate);
                break;
            //密码设置
            case R.id.persion_pass_set:
                Intent p_pass_set = new Intent(getActivity(), PassSettingActivity.class);
                getActivity().startActivity(p_pass_set);
                break;
            //关于
            case R.id.persion_about:
                Intent p_about = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(p_about);
                break;
            //退出
            case R.id.persion_quit:
                getActivity().finish();
                Toast.makeText(getActivity(), "你确定要退出？", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getActivity(),"还未开通会员",Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent_space = new Intent(getActivity(), SpaceActivity.class);
                    getActivity().startActivity(intent_space);
                }
            }
            @Override
            public void onFailure(String url) {

            }
        });

    }
}
