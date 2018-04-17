package com.yongxing.activity.demo01.activity;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.net.HttpCookie;
import java.util.HashMap;

import HConstants.HConstants;
import Utils.ControlUtils;

public class AddGroupActivity extends Activity implements View.OnClickListener {
    private ImageView topRight;
    private ImageView topLeft;
    private TextView topText;
    private Button saveGroupBtn;
    private String TAG="ymr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        saveGroupBtn = (Button) findViewById(R.id.add_group);

        topLeft = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topText = (TextView) findViewById(R.id.fragment_text_view);

        topLeft.setVisibility(View.VISIBLE);
        topRight.setVisibility(View.INVISIBLE);
        topText.setText("增加组");
        topLeft.setOnClickListener(this);
        saveGroupBtn.setOnClickListener(this);
    }

    private EditText getAddGroupName() {
        return (EditText) findViewById(R.id.add_group_name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_group:
                if (getAddGroupName().getText().equals("")) {
                    Toast.makeText(AddGroupActivity.this, "请输入组名!", Toast.LENGTH_SHORT).show();
                } else {

                    addGroup(getAddGroupName().getText().toString());
                }
                break;
            case R.id.top_left:
                finish();
                break;
        }
    }

    public void addGroup(String string) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(ControlUtils.userBean.getId()));
        map.put("groupName", string);
        map.put("groupType", "1");
        ControlUtils.getsEveryTime(HConstants.URL.AppAddMonitorGroup, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                Log.d(TAG, "onSuccess: "+result);
                if (resultBean.getResource()==1) {
                    Toast.makeText(AddGroupActivity.this, "添加成功!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {

                    Toast.makeText(AddGroupActivity.this, "添加失败!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String url) {
                Toast.makeText(AddGroupActivity.this, "添加失败!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
