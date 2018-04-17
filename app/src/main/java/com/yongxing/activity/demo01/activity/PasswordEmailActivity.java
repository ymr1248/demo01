package com.yongxing.activity.demo01.activity;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
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

import java.util.HashMap;
import java.util.List;

import HConstants.HConstants;
import Utils.ControlUtils;

public class PasswordEmailActivity extends Activity implements View.OnClickListener {
    private TextView topTitle;
    private ImageView topBack;
    private ImageView topRight;
    private Button checkCodeBtn;
    private EditText oldPassword;
    private EditText newPassword1;
    private EditText newPassword2;

    private WifiManager wifiManager;
    List<ScanResult> listb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_email);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        checkCodeBtn = (Button) findViewById(R.id.check_code_btn);
        topTitle = (TextView) findViewById(R.id.fragment_text_view);
        oldPassword= (EditText) findViewById(R.id.password_old);
        newPassword1= (EditText) findViewById(R.id.password_new1);
        newPassword2= (EditText) findViewById(R.id.password_new2);
        topBack = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setVisibility(View.INVISIBLE);
        topBack.setVisibility(View.VISIBLE);
        topTitle.setText("修改密码");
        topBack.setOnClickListener(this);
        checkCodeBtn.setOnClickListener(this);
        findViewById(R.id.save_btn).setOnClickListener(this);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        listb = wifiManager.getScanResults();
        //数组初始化要注意
        String[] listk=new String[listb.size()];
        if(listb!=null){
            for( int i=0;i<listb.size();i++){
                ScanResult scanResult = listb.get(i);
                listk[i]=scanResult.SSID;
            }
        }
        Log.d("qwer", "onCreate: "+listk.toString());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_code_btn:
                //点击获取手机验证码
                break;
            case R.id.save_btn:
                //TODO implement
                break;
            case R.id.top_left:
                finish();
                break;

        }
    }


    //保存新密码
    private void saveNewPassword() {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", String.valueOf(ControlUtils.userBean.getId()));
      /*  map.put("oldPassword", getPasswordOld().getText().toString());
        map.put("newPassword", getPasswordNew2().getText().toString());*/

        ControlUtils.getsEveryTime(HConstants.URL.AppUpdateMyPsw, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {

                if (result.equals("1")) {
                    Toast.makeText(PasswordEmailActivity.this,"密码修改成功！",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(PasswordEmailActivity.this,"密码修改失败！",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String url) {
                Toast.makeText(PasswordEmailActivity.this,"密码修改失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
