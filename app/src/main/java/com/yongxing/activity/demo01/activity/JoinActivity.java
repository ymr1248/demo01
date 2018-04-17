package com.yongxing.activity.demo01.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import com.yongxing.activity.demo01.R;

public class JoinActivity extends Activity implements View.OnClickListener {

    private TextView oneMonthTime;
    private TextView threeMonthTime;
    private TextView sixMonthTime;
    private TextView oneYearTime;
    private TextView twoYearTime;
    private TextView normalClear;
    private TextView ultraClear;
    private TextView payableNum;
    private Button settleAccountsBtn;

    private ImageView topRight;
    private ImageView topLeft;
    private TextView topText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        oneMonthTime = (TextView) findViewById(R.id.one_month_time);
        threeMonthTime = (TextView) findViewById(R.id.three_month_time);
        sixMonthTime = (TextView) findViewById(R.id.six_month_time);
        oneYearTime = (TextView) findViewById(R.id.one_year_time);
        twoYearTime = (TextView) findViewById(R.id.two_year_time);
        normalClear = (TextView) findViewById(R.id.normal_clear);
        ultraClear = (TextView) findViewById(R.id.ultra_clear);
        payableNum = (TextView) findViewById(R.id.payable_num);
        settleAccountsBtn = (Button) findViewById(R.id.settle_accounts_btn);
        settleAccountsBtn.setOnClickListener(this);

        topLeft = (ImageView) findViewById(R.id.top_left);
        topRight = (ImageView) findViewById(R.id.top_right);
        topText = (TextView) findViewById(R.id.fragment_text_view);

        topLeft.setVisibility(View.VISIBLE);
        topRight.setVisibility(View.INVISIBLE);
        topText.setText("开通会员");
        topLeft.setOnClickListener(this);
    }

    private EditText getOthersYearTime() {
        return (EditText) findViewById(R.id.others_year_time);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settle_accounts_btn:
                //TODO implement
                break;
            case R.id.top_left:
                finish();
                break;
        }
    }
}
