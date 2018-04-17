package com.yongxing.activity.demo01.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.HashMap;

import HConstants.HConstants;
import Utils.ControlUtils;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private TextView topTitle;
    private ImageView topBack;
    private ImageView topRight;
    private Button checkCodeBtn;
    private Button savePassBtn;
    private EditText phonePass;
    private EditText codePass;
    private EditText newPassword1;
    private EditText newPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        checkCodeBtn = (Button) findViewById(R.id.check_code_register);
        savePassBtn = (Button) findViewById(R.id.register_btn);
        phonePass = (EditText) findViewById(R.id.register_phone);
        codePass = (EditText) findViewById(R.id.register_code);
        newPassword1 = (EditText) findViewById(R.id.new_register_line1);
        newPassword2 = (EditText) findViewById(R.id.new_register_line2);
        topRight = findViewById(R.id.top_right);
        topBack = findViewById(R.id.top_left);
        topTitle = findViewById(R.id.fragment_text_view);
        topRight.setVisibility(View.INVISIBLE);
        topTitle.setText("注册用户");
        topBack.setOnClickListener(this);
        checkCodeBtn.setOnClickListener(this);
        savePassBtn.setOnClickListener(this);
    }

    private EditText getRegisterPhone(){
        return (EditText) findViewById(R.id.register_phone);
    }

    private EditText getRegisterCode(){
        return (EditText) findViewById(R.id.register_code);
    }

    private EditText getNewRegisterLine1(){
        return (EditText) findViewById(R.id.new_register_line1);
    }

    private EditText getNewRegisterLine2(){
        return (EditText) findViewById(R.id.new_register_line2);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_code_pass:
                //TODO implement
                getRegisterCodes();
                break;
            case R.id.save_new_pass_btn:
                //TODO implement
                String password1 = newPassword1.getText().toString();
                String password2 = newPassword2.getText().toString();
                if (password1.equals(password2)) {
                    saveNewUser();
                } else {
                    Toast.makeText(this, "密码输入有误！", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.top_left:
                finish();
                break;
        }
    }


    public void saveNewUser() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId",String.valueOf(ControlUtils.userBean.getId()));
        map.put("","");
        map.put("","");
        map.put("","");

        ControlUtils.getsEveryTime(HConstants.URL.AppGetSpace, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {

            }

            @Override
            public void onFailure(String url) {

            }
        });

    }

    public void getRegisterCodes() {
        HashMap<String, String> map = new HashMap<>();
        ControlUtils.getsEveryTime(HConstants.URL.AppGetSpace, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {

            }

            @Override
            public void onFailure(String url) {

            }
        });

    }
}
