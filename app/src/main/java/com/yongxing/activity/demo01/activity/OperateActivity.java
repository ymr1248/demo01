package com.yongxing.activity.demo01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.adapter.ProblemAdapter;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

public class OperateActivity extends Activity implements View.OnClickListener {

    private TextView operateTop;
    private ImageView operateLeft;
    private ImageView operateRight;
    private LinearLayout commonLayout;
    private LinearLayout deviceProblemLayout;
    private LinearLayout spaceCloudLayout;
    private LinearLayout otherProblemLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        commonLayout = (LinearLayout) findViewById(R.id.common_layout);
        deviceProblemLayout = (LinearLayout) findViewById(R.id.device_problem_layout);
        spaceCloudLayout = (LinearLayout) findViewById(R.id.space_cloud_layout);
        otherProblemLayout = (LinearLayout) findViewById(R.id.other_problem_layout);

        operateLeft = (ImageView) findViewById(R.id.top_left);
        operateTop = (TextView) findViewById(R.id.fragment_text_view);
        operateRight = (ImageView) findViewById(R.id.top_right);
        operateLeft.setVisibility(View.VISIBLE);
        operateRight.setVisibility(View.INVISIBLE);
        operateTop.setText("操作指导");

        deviceProblemLayout.setOnClickListener(this);
        spaceCloudLayout.setOnClickListener(this);
        otherProblemLayout.setOnClickListener(this);
        commonLayout.setOnClickListener(this);
        operateLeft.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_layout:
                Toast.makeText(this, "常见问题", Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(this, ProblemActivity.class);
                startActivity(intent1);
                break;
            case R.id.device_problem_layout:
                Intent intent2=new Intent(this, ProblemActivity.class);
                startActivity(intent2);
                Toast.makeText(this, "设备问题", Toast.LENGTH_LONG).show();
                break;
            case R.id.space_cloud_layout:
                Intent intent3=new Intent(this, ProblemActivity.class);
                startActivity(intent3);
                Toast.makeText(this, "云空间详情", Toast.LENGTH_LONG).show();
                break;
            case R.id.other_problem_layout:
                Intent intent4=new Intent(this, ProblemActivity.class);
                startActivity(intent4);
                Toast.makeText(this, "另外问题", Toast.LENGTH_LONG).show();
                break;
            //退出页面
            case R.id.top_left:
                finish();
                break;
        }
    }
}
