package com.yongxing.activity.demo01.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

public class WifiActivity extends Activity implements View.OnClickListener {

    private TextView wifiNameText;
    private TextView wifiPassword;
    private ImageView showWifiPassword;
    private TextView wifiGroup;
    private ImageView wifiCheckGroup;
    private TextView topTitle;
    private ImageView topBack;
    private ImageView topRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        wifiNameText = (TextView) findViewById(R.id.wifi_name_text);
        wifiPassword = (TextView) findViewById(R.id.wifi_password);
        showWifiPassword = (ImageView) findViewById(R.id.show_wifi_password);
        wifiGroup = (TextView) findViewById(R.id.wifi_group);
        wifiCheckGroup = (ImageView) findViewById(R.id.wifi_check_group);
        topTitle = (TextView) findViewById(R.id.fragment_text_view);
        topBack = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setVisibility(View.INVISIBLE);
        topBack.setVisibility(View.VISIBLE);
        topTitle.setText("WIFI只能快配");
        topBack.setOnClickListener(this);
        findViewById(R.id.save_btn).setOnClickListener(this);
        showWifiPassword.setOnClickListener(this);
        wifiCheckGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_wifi_password:


                break;
            case R.id.wifi_check_group:


                break;

            case R.id.top_left:
                finish();

                break;
            case R.id.save_btn:
                //TODO implement
                break;
        }
    }
}
