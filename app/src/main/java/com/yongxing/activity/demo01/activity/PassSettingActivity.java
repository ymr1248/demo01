package com.yongxing.activity.demo01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

public class PassSettingActivity extends Activity implements View.OnClickListener {

    private LinearLayout oldPassLayout;
    private LinearLayout phonePassLayout;
    private LinearLayout emailPassLayout;
    private TextView topTitle;
    private ImageView topBack;
    private ImageView topRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_setting);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        oldPassLayout = (LinearLayout) findViewById(R.id.old_pass_layout);
        phonePassLayout = (LinearLayout) findViewById(R.id.phone_pass_layout);
        emailPassLayout = (LinearLayout) findViewById(R.id.email_pass_layout);


        topTitle = (TextView) findViewById(R.id.fragment_text_view);
        topBack = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setVisibility(View.INVISIBLE);
        topBack.setVisibility(View.VISIBLE);
        topTitle.setText("修改密码");
        topBack.setOnClickListener(this);
        oldPassLayout.setOnClickListener(this);
        phonePassLayout.setOnClickListener(this);
        emailPassLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
            case R.id.old_pass_layout:
                Intent oldIntent = new Intent(this, PasswordActivity.class);
                this.startActivity(oldIntent);

                break;
            case R.id.phone_pass_layout:
                Intent phoneIntent = new Intent(this, PasswordPhoneActivity.class);
                this.startActivity(phoneIntent);

                break;
            case R.id.email_pass_layout:
                Intent emailIntent = new Intent(this, PasswordEmailActivity.class);
                this.startActivity(emailIntent);

                break;
        }

    }
}
