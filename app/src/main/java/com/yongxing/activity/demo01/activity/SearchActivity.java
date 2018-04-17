package com.yongxing.activity.demo01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.HashMap;
import java.util.Map;

import HConstants.HConstants;
import Utils.ControlUtils;
import Utils.SerializableMap;

public class SearchActivity extends Activity implements View.OnClickListener {

    private ImageView topRight;
    private ImageView topLeft;
    private TextView topText;
    private TextView topRightText;
    private LinearLayout noSeachMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        topLeft = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topText = (TextView) findViewById(R.id.fragment_text_view);
        topRightText = (TextView) findViewById(R.id.top_right_text);
        noSeachMonitor = (LinearLayout) findViewById(R.id.no_seach_monitor);
        noSeachMonitor.setVisibility(View.GONE);
        topRightText.setText("搜索");
        topRightText.setVisibility(View.VISIBLE);
        topLeft.setVisibility(View.VISIBLE);
        topRight.setVisibility(View.INVISIBLE);
        topText.setText("搜索设备");
        topLeft.setOnClickListener(this);
        topRightText.setOnClickListener(this);
    }

    private EditText getSearchContent() {
        return (EditText) findViewById(R.id.search_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.top_left:

                finish();
                break;
            case R.id.top_right_text:
                String search = getSearchContent().getText().toString();
                if (search.equals("")) {
                    Toast.makeText(SearchActivity.this, "搜索内容为空！", Toast.LENGTH_SHORT).show();
                } else {
                    seachMonitor();
                }
                break;
        }

    }

    /***
     * 查询设备
     */
    private void seachMonitor() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(ControlUtils.userBean.getId()));
        map.put("search", getSearchContent().getText().toString());
        ControlUtils.getsEveryTime(HConstants.URL.AppGetMonitorByName, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                getSearchContent().setVisibility(View.INVISIBLE);
                if (resultBean.getResource() == 1) {
                    String json = resultBean.getJsonString();
                    json = ControlUtils.chuangeStr(json);
                    noSeachMonitor.setVisibility(View.GONE);

                } else {

                }
            }

            @Override
            public void onFailure(String url) {

            }
        });

    }
}
