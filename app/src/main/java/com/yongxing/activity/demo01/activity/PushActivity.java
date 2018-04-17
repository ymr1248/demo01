package com.yongxing.activity.demo01.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.adapter.PushAdapter;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushActivity extends Activity implements View.OnClickListener {
    private TextView pushTop;
    private ImageView pushLeft;
    private ImageView pushRight;
    private ListView listView;
    private List<Map<String, Object>> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        listView = (ListView) findViewById(R.id.list_view);
        pushTop = (TextView) findViewById(R.id.fragment_text_view);
        pushLeft = (ImageView) findViewById(R.id.top_left);
        pushRight = (ImageView) findViewById(R.id.top_right);
        pushRight.setVisibility(View.INVISIBLE);
        pushLeft.setVisibility(View.VISIBLE);
        pushTop.setText("推送通知");
        pushLeft.setOnClickListener(this);
        mData = getData();
        PushAdapter adapter = new PushAdapter(this, mData);
        listView.setAdapter(adapter);
//        listView.setListAdapter(adapter);
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "G1");
        map.put("info", "google 1");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        list.add(map);

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_left:
                finish();
                break;

        }
    }
}
