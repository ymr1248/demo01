package com.yongxing.activity.demo01.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.Bean.UserBean;
import com.yongxing.activity.demo01.receiver.DemoApplication;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.service.ServiceTest;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.HashMap;

import HConstants.HConstants;
import Utils.ControlUtils;

public class LoginActivity extends Activity implements View.OnClickListener {

    private TextView userForget;
    private TextView userRegist;
    private Button signBtn;
    private CheckBox rememberPassword;
    //SharePreferences对象，用于记住账号
    private SharedPreferences sp;
    private String username;
    private String password;

    private EditText usernameEdit;
    private EditText passwordEdit;
    String TAG = "YMR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DemoApplication.setMainActivity(this);
        Intent intent = new Intent(this, ServiceTest.class);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        startService(intent);
        userForget = (TextView) findViewById(R.id.user_forget);
        userRegist = (TextView) findViewById(R.id.user_regist);
        usernameEdit = (EditText) findViewById(R.id.user_name);
        passwordEdit = (EditText) findViewById(R.id.user_password);
        signBtn = (Button) findViewById(R.id.user_sign);
        rememberPassword = (CheckBox) findViewById(R.id.user_remember_password);
        signBtn.setOnClickListener(this);
        userRegist.setOnClickListener(this);
        userForget.setOnClickListener(this);
        //获取保存在SharePreferences里面的账号信息，实现自动登录
        sp = getSharedPreferences("accountInfo", Context.MODE_PRIVATE);
        rememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rememberPassword.isChecked()) {

                    System.out.println("记住账号框未选中状态");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                } else {

                    System.out.println("记住账号框未选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }
            }
        });

        if (sp.getBoolean("ISCHECK", false)) {

            rememberPassword.setChecked(true);

            try {
                username = sp.getString("ACCOUNTVALUE", "");
                System.out.println("<<<<<<<<<<<<" + "账号" + username);
            } catch (Exception e) {
                // TODO: handle exception
            }

            usernameEdit.setText(username);

            try {
                password = sp.getString("PASSWORDVALUE", "");
                System.out.println("<<<<<<<<<<<<" + "密码" + password);

            } catch (Exception e) {
                // TODO: handle exception
            }

            passwordEdit.setText(password);

          /*  if(sp.getBoolean("AUTO_ISCHECK", false)){
                rememberPassword.setChecked(true);
                initLogin();
            }*/
        }
    }


    //登录执行保存账号密码到本地
    public void initLogin() {
        //显示进度条

        username = usernameEdit.getText().toString();
        password = passwordEdit.getText().toString();

        if (rememberPassword.isChecked()) {

            SharedPreferences.Editor editor = sp.edit();

            try {
                editor.putString("ACCOUNTVALUE", username);
            } catch (Exception e) {
                Toast.makeText(this, "账号保存异常", Toast.LENGTH_SHORT).show();
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            try {
                editor.putString("PASSWORDVALUE", password);
            } catch (Exception e) {
                Toast.makeText(this, "密码保存异常", Toast.LENGTH_SHORT).show();
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            editor.commit();
        }
    }

    public void loginCheck(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("account", username);
        map.put("password", password);
        map.put("appId",ControlUtils.mRegId);
        ControlUtils.getsEveryTime(HConstants.URL.LOGIN, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                Log.d("YMR", "onSuccess: " + resultBean.getJsonString());
                String string = resultBean.getJsonString();
                string = ControlUtils.chuangeStr(string);
                Log.d(TAG, "onSuccess: " + string);
                if (resultBean.getResource() == HConstants.EVENT.LOGINSTATE) {
                    String json = string;
                    UserBean userBean = ControlUtils.getEntityFromJson(json, UserBean.class);
                    ControlUtils.userBean = userBean;
                    ControlUtils.ID = userBean.getId();
                    Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败！账户或密码错误！", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(String url) {
                Toast.makeText(LoginActivity.this, "网络异常登录失败！", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + url);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_sign:
                //TODO implement
                username = usernameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                initLogin();
                loginCheck(username, password);


                break;

            case R.id.user_forget:
                Intent intentF = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intentF);
                break;

            case R.id.user_regist:

                Intent intentR = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentR);
                break;
        }
    }
}
