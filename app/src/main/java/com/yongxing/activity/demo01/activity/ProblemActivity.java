package com.yongxing.activity.demo01.activity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.adapter.HomeAdapter;
import com.yongxing.activity.demo01.adapter.ProblemAdapter;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

import java.util.ArrayList;

public class ProblemActivity extends Activity implements View.OnClickListener {

    private RecyclerView recyclerviewProblem;
    private ImageView topBack;
    private ImageView topRight;
    private TextView topTitle;
    private ArrayList<String> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_problem);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.blue);
        recyclerviewProblem = (RecyclerView) findViewById(R.id.recyclerview_problem);

        topBack = (ImageView) findViewById(R.id.top_left);
        topTitle = (TextView) findViewById(R.id.fragment_text_view);
        topRight = (ImageView) findViewById(R.id.top_right);
        topRight.setVisibility(View.INVISIBLE);
        topBack.setVisibility(View.VISIBLE);
        topTitle.setText("常见问题");
        topBack.setOnClickListener(this);
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add("问题"+i );
        }
        final ProblemAdapter recycleAdapter = new ProblemAdapter(this, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerviewProblem.setLayoutManager(layoutManager);
        recyclerviewProblem.getItemAnimator().setChangeDuration(0);
        //设置Adapter
        recyclerviewProblem.setAdapter(recycleAdapter);
        //        //设置分隔线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.top_left:
                finish();
                break;

        }

    }
}
