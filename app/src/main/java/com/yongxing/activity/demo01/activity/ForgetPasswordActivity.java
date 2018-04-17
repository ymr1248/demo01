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

public class ForgetPasswordActivity extends Activity implements View.OnClickListener {
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
        setContentView(R.layout.activity_forget_password);
        StatusBarUtils.setWindowStatusBarColor(ForgetPasswordActivity.this, R.color.blue);
        checkCodeBtn = (Button) findViewById(R.id.check_code_pass);
        savePassBtn = (Button) findViewById(R.id.save_new_pass_btn);
        phonePass = (EditText) findViewById(R.id.forget_password_phone);
        codePass = (EditText) findViewById(R.id.forget_password_code);
        newPassword1 = (EditText) findViewById(R.id.new_password_line1);
        newPassword2 = (EditText) findViewById(R.id.new_password_line2);
        topRight = findViewById(R.id.top_right);
        topBack = findViewById(R.id.top_left);
        topTitle = findViewById(R.id.fragment_text_view);
        topRight.setVisibility(View.INVISIBLE);
        topTitle.setText("忘记密码");
        topBack.setOnClickListener(this);
        checkCodeBtn.setOnClickListener(this);
        savePassBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_code_pass:
                //TODO implement

                getPassowrdCode();
                break;
            case R.id.save_new_pass_btn:
                //TODO implement
                String password1 = newPassword1.getText().toString();
                String password2 = newPassword2.getText().toString();
                if (password1.equals(password2)) {
                    saveNewPassword();
                } else {
                    Toast.makeText(this, "密码输入有误！", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.top_left:
                finish();
                break;
        }
    }

    public void saveNewPassword() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId",String.valueOf(ControlUtils.userBean.getId()));
        map.put("",phonePass.getText().toString());
        map.put("",newPassword1.getText().toString());
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

    public void getPassowrdCode() {
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
