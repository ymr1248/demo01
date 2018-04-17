package com.yongxing.activity.demo01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

/**
 * Created by Administrator on 2018-01-17.
 */

public class AboutActivity extends AppCompatActivity {
    private ImageView top_back;
    private ImageView top_share;
    private TextView top_title;
    private TextView aboutComanyName;
    private TextView aboutComanyAddress;
    private TextView aboutComanyPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        top_back = (ImageView) findViewById(R.id.top_left);
        top_share= (ImageView) findViewById(R.id.top_right);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        aboutComanyName = (TextView) findViewById(R.id.about_comany_name);
        aboutComanyAddress = (TextView) findViewById(R.id.about_comany_address);
        aboutComanyPhone = (TextView) findViewById(R.id.about_comany_phone);
        top_back.setImageDrawable(getResources().getDrawable(R.drawable.all_return));
        aboutComanyAddress.setText("广东省中山市横栏镇永兴北路");
        aboutComanyName.setText("中山长兴光电电器制造有限公司");
        aboutComanyPhone.setText("0760-85715236");
        top_back.setVisibility(View.VISIBLE);
        top_share.setVisibility(View.INVISIBLE);
        top_title= (TextView) findViewById(R.id.fragment_text_view);
        top_title.setText(R.string.about_us);
        top_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
