package com.yongxing.activity.demo01.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.MonitorGroupBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupListBean;
import com.yongxing.activity.demo01.Bean.MonitorListBean;
import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import HConstants.HConstants;
import Utils.ControlUtils;

public class AddDevActivity extends Activity implements View.OnClickListener {

    private ImageView addDevScan;
    private ImageView addDevPassshow;
    private ImageView addDevSelgroup;
    private ImageView addDevRight;
    private ImageView addDevLeft;
    private TextView addDevText;
    private EditText addDevId;
    private EditText addDevUserName;
    private EditText addDevPassword;
    private EditText addDevGroup;
    ArrayList<MonitorGroupBean> listgroup;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    //监控器组的名称数组
    String[] lesson = null;
    //监控器组ID
    int groupID;
    String TAG = "delman";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dev);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        addDevScan = (ImageView) findViewById(R.id.add_dev_scan);
        addDevPassshow = (ImageView) findViewById(R.id.add_dev_passshow);
        addDevSelgroup = (ImageView) findViewById(R.id.add_dev_selgroup);

        addDevRight = (ImageView) findViewById(R.id.top_right);
        addDevLeft = (ImageView) findViewById(R.id.top_left);
        addDevText = (TextView) findViewById(R.id.fragment_text_view);
        addDevId = (EditText) findViewById(R.id.add_dev_id);
        addDevUserName = (EditText) findViewById(R.id.add_dev_username);
        addDevPassword = (EditText) findViewById(R.id.add_dev_password);
        addDevGroup = (EditText) findViewById(R.id.add_dev_group);
        addDevRight.setVisibility(View.INVISIBLE);
        addDevLeft.setVisibility(View.VISIBLE);

        addDevText.setText("手动添加设备");

        addDevPassshow.setOnClickListener(this);
        addDevScan.setOnClickListener(this);
        addDevSelgroup.setOnClickListener(this);
        addDevLeft.setOnClickListener(this);
        findViewById(R.id.add_dev_defime).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_dev_defime:
                //TODO implement

                saveMonitor();
                break;
            //分组选择
            case R.id.add_dev_selgroup:
                //TODO implement
                getGroup();

                break;
            //密码明文显示
            case R.id.add_dev_passshow:
                //TODO implement
                break;
            //扫描二维码
            case R.id.add_dev_scan:
                //TODO implement
                break;
            case R.id.top_left:
                finish();
                //TODO implement
                break;
        }
    }

    private void saveMonitor() {
        HashMap<String, String> map = new HashMap<>();
        map.put("monitorInfo", "");
        map.put("monitorIp", "");
        map.put("monitorLocation", "中山");
        map.put("monitorName", "公司大堂");
        map.put("monitorStatus", "0");
        map.put("monitorType", "A00001");
        map.put("groupId", String.valueOf(groupID));
        map.put("userId", String.valueOf(ControlUtils.userBean.getId()));
        map.put("storageLocation", "");
        ControlUtils.getsEveryTime(HConstants.URL.AppAddMonitor, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                if (resultBean.getResource() == 1) {
                    finish();
                    Toast.makeText(AddDevActivity.this, "设备添加成功", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(AddDevActivity.this, "设备添加失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String url) {
                Toast.makeText(AddDevActivity.this, "设备添加失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //获取监控器组列表
    private void getGroup() {
        HashMap<String, String> map = new HashMap<>();
        map.put("pageNum", "1");
        map.put("userId", String.valueOf(ControlUtils.userBean.getId()));
        ControlUtils.getsEveryTime(HConstants.URL.MonitorGroupList, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                if (resultBean.getResource() == 1) {
                    String string = resultBean.getJsonString();
                    string = ControlUtils.chuangeStr(string);
                    MonitorGroupListBean monitorBean = ControlUtils.getEntityFromJson(string, MonitorGroupListBean.class);
                    listgroup = (ArrayList<MonitorGroupBean>) monitorBean.getDataList();
                    lesson = new String[listgroup.size()];
                    for (int i = 0; i < listgroup.size(); i++) {

                        Log.d(TAG, "onSuccess: " + listgroup.get(i).getGroupName());
                        lesson[i] = listgroup.get(i).getGroupName();
                        Log.d(TAG, "onSuccess: " + lesson[i]);
                    }

                    //弹出框选择分组
                    alert = null;
                    builder = new AlertDialog.Builder(AddDevActivity.this);
                    alert = builder
                            .setTitle("选择组！")
                            .setItems(lesson, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d(TAG, "onClick: " + lesson[which]);
                                    Toast.makeText(AddDevActivity.this, "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                                    addDevGroup.setText(lesson[which]);
                                    groupID = listgroup.get(which).getGroupId();
                                }
                            }).create();

                    alert.show();
                } else {
                    Toast.makeText(AddDevActivity.this, "获取设备组失败！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String url) {
                Toast.makeText(AddDevActivity.this, "获取设备组失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
