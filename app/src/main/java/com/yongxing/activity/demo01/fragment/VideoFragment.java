package com.yongxing.activity.demo01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.MonitorBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupListBean;
import com.yongxing.activity.demo01.Bean.MonitorListBean;
import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.Bean.UserBean;
import com.yongxing.activity.demo01.activity.MonitorActivity;
import com.yongxing.activity.demo01.activity.SelectVideoActivity;
import com.yongxing.activity.demo01.adapter.HomeAdapter;
import com.yongxing.activity.demo01.adapter.VideoAdapter;
import com.yongxing.activity.demo01.utils.Constants;
import com.yongxing.activity.demo01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HConstants.HConstants;
import Utils.ControlUtils;
import Utils.SerializableMap;


/**
 * Created by Kevin on 2016/11/28.
 * Blog:http://blog.csdn.net/student9128
 * Description:
 */

public class VideoFragment extends Fragment implements View.OnClickListener,VideoAdapter.MyItemClickListener  {
    public static VideoFragment newInstance(String s) {
        VideoFragment homeFragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private ImageView locationRight;
    private ImageView locationLeft;
    private RecyclerView recyclerView;
    private List<Map<String, Object>> mData;
    private List<Map<String, Object>> mDataB = new ArrayList<>();
    private List<String> mDatas;
    private TextView locationTitle;
    private TextView locationRigntText;
    VideoAdapter recycleAdapter;

    private List<String> groupkey = new ArrayList<String>();
    private String TAG="YMR";
    ArrayList<MonitorGroupBean> listgroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mlocation, container, false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        locationTitle = (TextView) view.findViewById(R.id.fragment_text_view);
        locationRight = (ImageView) view.findViewById(R.id.top_right);
        locationLeft = (ImageView) view.findViewById(R.id.top_left);
        recyclerView = (RecyclerView) view.findViewById(R.id.location_list);
        locationRigntText = (TextView) view.findViewById(R.id.top_right_text);
        locationLeft.setVisibility(View.INVISIBLE);
        locationTitle.setText(s);
        locationRight.setOnClickListener(this);
        locationRigntText.setVisibility(View.VISIBLE);
        locationRigntText.setText("筛选");
        locationRigntText.setOnClickListener(this);
        locationRight.setImageResource(R.drawable.video_screen);
        mData = getDatas();
      /* recycleAdapter = new VideoAdapter(getActivity(), mData, groupkey);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        //        //设置分隔线
        //        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recycleAdapter.setOnLongItemClickListener(new VideoAdapter.OnRecyclerViewLongItemClickListener() {
            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getActivity(), "AAAAAAAAAAA", Toast.LENGTH_LONG).show();
            }
        });
        recycleAdapter.setItemClickListener( this);*/

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {

        String s = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map = mData.get(position);
        s = map.get("id").toString();
        if (mData.get(position).get("group").equals("")) {
//            Boolean.parseBoolean((String)
            if (!(Boolean) mData.get(position ).get("show")) {
                getListData(mData.get(position).get("id").toString(), "1", position);

            } else {
                for (int i = 0; i < mData.size(); i++) {

                    if (mData.get(i).get("group").equals(s)) {
                        mData.get(i).put("show", false);
                    }
                }
                mData.get(position).put("show",false);
            }


            recycleAdapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(getActivity(), MonitorActivity.class);
            Map<String, Object> map1=  mData.get(position);
            SerializableMap tmpmap=new SerializableMap();
            Bundle bundle=new Bundle();
            tmpmap.setMap(map1);
            bundle.putSerializable("monitorinfo", tmpmap);
            intent.putExtras(bundle);
            getActivity().startActivity(intent);
            //不同的视频画面跳转
        }


    }


  /*  public List<Map<String, Object>> getDatas() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        groupkey.add("A");
        groupkey.add("B");
        Map<String, Object> map = new HashMap<String, Object>();
        map = new HashMap<String, Object>();
        map.put("title", "A");
        map.put("info", "连接成功");
        map.put("show", true);
        map.put("group", "1A");
        list.add(map);
        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, Object>();
            map.put("title", "A组--00" + i);
            map.put("info", "连接成功");
            map.put("show", true);
            map.put("group", "A");
            mDataB.add(map);
            list.add(map);
        }

        map = new HashMap<String, Object>();
        map.put("title", "B");
        map.put("info", "连接成功");
        map.put("show", true);
        map.put("group", "1B");
        list.add(map);
        for (int i = 0; i < 20; i++) {
            map = new HashMap<String, Object>();
            map.put("title", "B组--00" + i);
            map.put("info", "连接成功");
            map.put("show", true);
            map.put("group", "B");
            list.add(map);
        }

        return list;
    }
*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_right:
                Intent intent = new Intent(getActivity(), SelectVideoActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.top_right_text:
                Intent intent1 = new Intent(getActivity(), SelectVideoActivity.class);
                getActivity().startActivity(intent1);
                break;
        }
    }
//    public List<Map<String, Object>> getData() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        groupkey.add("A");
//        groupkey.add("B");
//        Map<String, Object> map = new HashMap<String, Object>();
//        map = new HashMap<String, Object>();
//        map.put("title", "A");
//        map.put("info", "连接成功");
//        map.put("show", true);
//        map.put("group", "1A");
//        list.add(map);
//        for (int i = 0; i < 10; i++) {
//            map = new HashMap<String, Object>();
//            map.put("title", "A组--00" + i);
//            map.put("info", "连接成功");
//            map.put("show", true);
//            map.put("group", "A");
//            mDataB.add(map);
//            list.add(map);
//        }
//
//        map = new HashMap<String, Object>();
//        map.put("title", "B");
//        map.put("info", "连接成功");
//        map.put("show", true);
//        map.put("group", "1B");
//        list.add(map);
//        for (int i = 0; i < 10; i++) {
//            map = new HashMap<String, Object>();
//            map.put("title", "B组--00" + i);
//            map.put("info", "连接成功");
//            map.put("show", true);
//            map.put("group", "B");
//            list.add(map);
//        }
//
//        return list;
//    }
    private void getListData(String groupId, String pageunm, final int num) {

        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, String> map = new HashMap<>();
        UserBean userBean = ControlUtils.userBean;
        map.put("userId", String.valueOf(userBean.getId()));
        map.put("groupId", groupId);
        map.put("pageNum", "1");
        ControlUtils.getsEveryTime(HConstants.URL.AppMonitorList, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                String json = resultBean.getJsonString();
                json = json.replace("/", "");
                MonitorListBean monitorListBean = ControlUtils.getEntityFromJson(json, MonitorListBean.class);
                Log.d(TAG, "onSuccess: " + monitorListBean.getDataList().size());
                ArrayList<MonitorBean> monitorBeans = (ArrayList<MonitorBean>) monitorListBean.getDataList();

                for (int i = 0; i < monitorBeans.size(); i++) {

                    Map<String, Object> map = new HashMap<String, Object>();
                    map = new HashMap<String, Object>();
                    map.put("title", monitorBeans.get(i).getMonitorName());
                    map.put("info", "");
                    map.put("show", true);
                    map.put("group", String.valueOf(monitorBeans.get(i).getMonitorGroup().getGroupId()));
                    map.put("type", monitorBeans.get(i).getMonitorType());
                    map.put("id", String.valueOf(monitorBeans.get(i).getId()));
                    mData.add(num + 1, map);

                }
                Log.d(TAG, "onSuccess: " + mData.toString());
                mData.get(num).put("show",true);
                recycleAdapter.notifyDataSetChanged();
//                setData(mData);
            }

            @Override
            public void onFailure(String url) {
                Log.d(TAG, "onFailure: " + url);
                Toast.makeText(getActivity(), url, Toast.LENGTH_LONG).show();
            }
        });

    }

    public List<Map<String, Object>> getDatas() {
        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, String> map = new HashMap<>();
        UserBean userBean = ControlUtils.userBean;
        map.put("userId", String.valueOf(userBean.getId()));
        map.put("pageNum", "1");
        ControlUtils.getsEveryTime(HConstants.URL.MonitorGroupList, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {

                String json = resultBean.getJsonString();
                json = json.replace("/", "");
                MonitorGroupListBean monitorBean = ControlUtils.getEntityFromJson(json, MonitorGroupListBean.class);


                listgroup = (ArrayList<MonitorGroupBean>) monitorBean.getDataList();
                for (int i = 0; i < listgroup.size(); i++) {
                    groupkey.add(String.valueOf(listgroup.get(i).getGroupId()));
                    Map<String, Object> map = new HashMap<String, Object>();
                    map = new HashMap<String, Object>();
                    map.put("title", listgroup.get(i).getGroupName());
                    map.put("info", "");
                    map.put("show", false);
                    map.put("group", "");
                    map.put("type", listgroup.get(i).getGroupType());
                    map.put("id", String.valueOf(listgroup.get(i).getGroupId()));
                    mData.add(map);

                }
                setData(mData);
            }

            @Override
            public void onFailure(String url) {
                Log.d(TAG, "onFailure: " + url);
                Toast.makeText(getActivity(), url, Toast.LENGTH_LONG).show();
            }
        });

        return list;
    }

    private void setData(List<Map<String, Object>> list) {

        recycleAdapter = new VideoAdapter(getActivity(), list, groupkey);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        //设置分隔线
//      recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recycleAdapter.setItemClickListener(this);
    }
}
