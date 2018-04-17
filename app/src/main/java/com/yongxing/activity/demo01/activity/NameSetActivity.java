package com.yongxing.activity.demo01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

public class NameSetActivity extends Activity implements View.OnClickListener {
    private TextView textView;
    private ImageView backImage;
    private ImageView saveImange;
    private EditText setName;
    private TextView topRightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_set);
        Intent intent=getIntent();
        String username=intent.getStringExtra("name");
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        textView = (TextView) findViewById(R.id.fragment_text_view);
        backImage = (ImageView) findViewById(R.id.top_left);
        saveImange = (ImageView) findViewById(R.id.top_right);
        setName = (EditText) findViewById(R.id.set_name);
        textView.setText("昵称修改");
        backImage.setVisibility(View.VISIBLE);
        saveImange.setVisibility(View.INVISIBLE);
        topRightText.setText("保存");
        setName.setText(username);
        topRightText.setOnClickListener(this);
        backImage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回上一层
            case R.id.top_left:
                finish();
                break;
            //保存电话号码
            case R.id.top_right_text:
                String s = setName.getText().toString();
                break;
        }
    }
}
