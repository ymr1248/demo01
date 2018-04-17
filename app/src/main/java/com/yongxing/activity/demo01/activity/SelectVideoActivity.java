package com.yongxing.activity.demo01.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.MonitorBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupListBean;
import com.yongxing.activity.demo01.Bean.MonitorListBean;
import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.Bean.UserBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HConstants.HConstants;
import Utils.ControlUtils;

public class SelectVideoActivity extends Activity implements View.OnClickListener {

    private LinearLayout selectGroupLayout;
    private TextView checkGroupText;
    private LinearLayout selectDeviceLayout;
    private TextView checkDeviceText;
    private LinearLayout selectStorageLayout;
    private TextView checkStorageText;
    private LinearLayout selectBeginLayout;
    private TextView checkBeginText;
    private LinearLayout selectEndLayout;
    private TextView checkEndText;
    private TextView topTitle;
    private ImageView topBack;
    private ImageView topRight;
    private Button selectBtn;
    private boolean[] checkItems;

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    ArrayList<MonitorGroupBean> listgroup;
    private String TAG = "YMR";
    String[] group;
    String[] monitorGroup;

    private int checkGroupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_video);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);

        selectBtn = (Button) findViewById(R.id.select_btn);

        selectGroupLayout = (LinearLayout) findViewById(R.id.select_group_layout);
        checkGroupText = (TextView) findViewById(R.id.check_group_text);
        selectDeviceLayout = (LinearLayout) findViewById(R.id.select_device_layout);
        checkDeviceText = (TextView) findViewById(R.id.check_device_text);
        selectStorageLayout = (LinearLayout) findViewById(R.id.select_storage_layout);
        checkStorageText = (TextView) findViewById(R.id.check_storage_text);
        selectBeginLayout = (LinearLayout) findViewById(R.id.select_begin_layout);
        checkBeginText = (TextView) findViewById(R.id.check_begin_text);
        selectEndLayout = (LinearLayout) findViewById(R.id.select_end_layout);
        checkEndText = (TextView) findViewById(R.id.check_end_text);
        topTitle = (TextView) findViewById(R.id.fragment_text_view);
        topBack = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setVisibility(View.INVISIBLE);
        topBack.setVisibility(View.VISIBLE);
        topTitle.setText("筛选");
        topBack.setOnClickListener(this);
        selectGroupLayout.setOnClickListener(this);
        selectBeginLayout.setOnClickListener(this);
        selectDeviceLayout.setOnClickListener(this);
        selectStorageLayout.setOnClickListener(this);
        selectEndLayout.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
        getGroupData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_btn:

                break;

            case R.id.select_group_layout:
                alert = null;
                builder = new AlertDialog.Builder(this);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("选择分组")
                        .setItems(group, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkGroupText.setText(group[which]);
//                                checkGroupId=listgroup.get(which).getGroupId();
                                getListData(String.valueOf(listgroup.get(which).getGroupId()), "1");
//                                Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            case R.id.select_device_layout:

                alert = null;
                builder = new AlertDialog.Builder(this);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("设备选择！")
                        .setItems(monitorGroup, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkDeviceText.setText(monitorGroup[which]);

//                                Toast.makeText(getApplicationContext(), "你选择了" + fruits[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
                break;
            case R.id.select_storage_layout:
                checkStorageText.setText("");

                break;
            case R.id.select_begin_layout:

                checkBeginText.setText("");
                break;
            case R.id.select_end_layout:
                checkEndText.setText("");

                break;
            case R.id.top_left:

                finish();
                break;
        }
    }

    private void getGroupData() {
        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, String> map = new HashMap<>();
        UserBean userBean = ControlUtils.userBean;
        map.put("userId", String.valueOf(userBean.getId()));
        map.put("pageNum", "1");
        ControlUtils.getsEveryTime(HConstants.URL.MonitorGroupList, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {

                String json = resultBean.getJsonString();
                json = ControlUtils.chuangeStr(json);
                MonitorGroupListBean monitorBean = ControlUtils.getEntityFromJson(json, MonitorGroupListBean.class);


                listgroup = (ArrayList<MonitorGroupBean>) monitorBean.getDataList();
                group = new String[listgroup.size()];
                for (int i = 0; i < listgroup.size(); i++) {
                    group[i] = listgroup.get(i).getGroupName();
                  /*   groupkey.add(String.valueOf(listgroup.get(i).getGroupId()));
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = new HashMap<String, Object>();
                    map.put("title", listgroup.get(i).getGroupName());
                    map.put("info", "");
                    map.put("show", false);
                    map.put("group", "");
                    map.put("type", listgroup.get(i).getGroupType());
                    map.put("id", String.valueOf(listgroup.get(i).getGroupId()));
                    mData.add(map);
                     setData(mData);
*/
                }

            }

            @Override
            public void onFailure(String url) {
                Log.d(TAG, "onFailure: " + url);
                Toast.makeText(SelectVideoActivity.this, url, Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getListData(String groupId, String pageunm) {

        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, String> map = new HashMap<>();
        UserBean userBean = ControlUtils.userBean;
        map.put("userId", String.valueOf(userBean.getId()));
        map.put("groupId", groupId);
        map.put("pageNum", pageunm);
        ControlUtils.getsEveryTime(HConstants.URL.AppMonitorList, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                String json = resultBean.getJsonString();
                json = ControlUtils.chuangeStr(json);
                MonitorListBean monitorListBean = ControlUtils.getEntityFromJson(json, MonitorListBean.class);
                Log.d(TAG, "onSuccess: " + monitorListBean.getDataList().size());
                ArrayList<MonitorBean> monitorBeans = (ArrayList<MonitorBean>) monitorListBean.getDataList();
                monitorGroup = new String[monitorBeans.size()];
                for (int i = 0; i < monitorBeans.size(); i++) {

                    monitorGroup[i] = monitorBeans.get(i).getMonitorName();
                }
//                setData(mData);
            }

            @Override
            public void onFailure(String url) {
                Log.d(TAG, "onFailure: " + url);
                Toast.makeText(SelectVideoActivity.this, url, Toast.LENGTH_LONG).show();
            }
        });

    }
}
