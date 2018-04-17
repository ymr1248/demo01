package com.yongxing.activity.demo01.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yongxing.activity.demo01.Bean.MonitorBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupBean;
import com.yongxing.activity.demo01.Bean.MonitorGroupListBean;
import com.yongxing.activity.demo01.Bean.MonitorListBean;
import com.yongxing.activity.demo01.Bean.ResultBean;
import com.yongxing.activity.demo01.Bean.UserBean;
import com.yongxing.activity.demo01.activity.AboutActivity;
import com.yongxing.activity.demo01.activity.AddDevActivity;
import com.yongxing.activity.demo01.activity.AddGroupActivity;
import com.yongxing.activity.demo01.activity.MonitorActivity;
import com.yongxing.activity.demo01.activity.SearchActivity;
import com.yongxing.activity.demo01.activity.WifiActivity;
import com.yongxing.activity.demo01.adapter.HomeAdapter;
import com.yongxing.activity.demo01.service.ServiceTest;
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
 * Created by Delman 2018/1/2.
 * <p>
 * Description: HomeFragment
 */

public class HomeFragment extends Fragment implements View.OnClickListener,
        HomeAdapter.MyItemClickListener, HomeAdapter.MyItemLongClickListener {
    public static HomeFragment newInstance(String s) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    private ImageView home_right;
    private ImageView homeLeft;
    private RecyclerView recyclerView;
    private List<Map<String, Object>> mData;
    private List<Map<String, Object>> mDataB = new ArrayList<>();
    private List<String> mDatas;
    private PopupWindow mMenuPop;

    private int PopWidth;
    private int PopHeight;

    private TextView meunText1;
    private TextView meunText2;
    private TextView meunText3;
    private TextView meunText4;
    private TextView meunText5;
    private List<String> groupkey = new ArrayList<String>();
    Intent intent;
    HomeAdapter recycleAdapter;
    String TAG = "YMR";
    ArrayList<MonitorGroupBean> listgroup;
    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mhome, container, false);
        Bundle bundle = getArguments();
        String s = bundle.getString(Constants.ARGS);
        TextView textView = (TextView) view.findViewById(R.id.fragment_text_view);
        textView.setText(s);
        home_right = (ImageView) view.findViewById(R.id.top_right);
        homeLeft = (ImageView) view.findViewById(R.id.top_left);
        homeLeft.setVisibility(View.INVISIBLE);
        //切换首页右边图标
        home_right.setImageResource(R.drawable.home_add_right);
        home_right.setOnClickListener(this);
        intent = new Intent(getActivity(), ServiceTest.class);
        getActivity().startService(intent);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_list);


        return view;
    }

    /**
     * 显示悬浮菜单
     */
    private void showLevitateMenu() {


        //创建popwindow

        getPopMenu();
        //获取ImageView控件在手机屏幕的位置
        int[] location = new int[2];
        home_right.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];

        /**
         * popwindow显示的位置
         * 参数一：基于某控件，一般在popupWindow.showAsDropDown()中比较有用，该处作用不大
         * 参数二：见名知意，写默认即可
         * 参数三：popupWindow在屏幕上显示位置的x坐标
         * 参数四：popupWindow在屏幕上显示位置的y左边
         */

        if (mMenuPop == null) {
            initMenuPop();
        }
        mMenuPop.showAtLocation(home_right, Gravity.NO_GRAVITY,
                home_right.getLeft() - PopWidth + home_right.getWidth() / 4,
                y + home_right.getHeight());

    }


    /**
     * 获取PopupWindow实例 .分类
     */
    private void getPopMenu() {

        if (null != mMenuPop) {
            //动画
//            mRotate(mImageView);
            //关闭
            mMenuPop.dismiss();
            mMenuPop = null;
            return;
        } else {
            //初始化popupWindow弹窗
            initMenuPop();
        }
    }


    /**
     * 初始化popWindow
     */
    private void initMenuPop() {
        // 获取自定义布局文件pop.xml的视图
        View view = View.inflate(getActivity(), R.layout.home_meun_layout, null);

        //测量view的宽高，由于popupwindow没有测量的方法，只能测量内部view的宽高
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        PopWidth = view.getMeasuredWidth();
        PopHeight = view.getMeasuredHeight();

        //下面这两个必须有！！
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        // PopupWindow(布局，宽度，高度) 注意，此处宽高应为-2也就是wrap_content
        mMenuPop = new PopupWindow(view, 600, -2, true);

        // 重写onKeyListener,按返回键消失
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    mMenuPop.dismiss();
                    mMenuPop = null;
                    return true;
                }
                return false;
            }
        });

        //点击其他地方消失
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mMenuPop != null && mMenuPop.isShowing()) {
                    mMenuPop.dismiss();
                    mMenuPop = null;
                    return true;
                }
                return false;
            }
        });

        meunText1 = (TextView) view.findViewById(R.id.meun_text1);
        meunText2 = (TextView) view.findViewById(R.id.meun_text2);
        meunText3 = (TextView) view.findViewById(R.id.meun_text3);
        meunText4 = (TextView) view.findViewById(R.id.meun_text4);
        meunText5 = (TextView) view.findViewById(R.id.meun_text5);
        meunText1.setOnClickListener(this);
        meunText2.setOnClickListener(this);
        meunText3.setOnClickListener(this);
        meunText4.setOnClickListener(this);
        meunText5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //右上角+ 号
            case R.id.top_right:

                showLevitateMenu();
                break;
            //增加组
            case R.id.meun_text1:
                Intent intent1 = new Intent(getActivity(), AddGroupActivity.class);
                getActivity().startActivity(intent1);
                break;
            //设备搜索
            case R.id.meun_text2:
                Intent intent2 = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent2);
                break;
            //WIFI只能快配
            case R.id.meun_text3:
                Intent intent3 = new Intent(getActivity(), WifiActivity.class);
                getActivity().startActivity(intent3);
                break;
            //手动添加设备
            case R.id.meun_text4:
                Intent intent4 = new Intent(getActivity(), AddDevActivity.class);
                getActivity().startActivity(intent4);
                break;
            //购买设备
            case R.id.meun_text5:
                Intent intent5 = new Intent(getActivity(), AboutActivity.class);

                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {

        String s = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map = mData.get(position);
        s = map.get("id").toString();
        if (mData.get(position).get("group").equals("")) {
            if (!(Boolean) mData.get(position).get("show")) {
                getListData(mData.get(position).get("id").toString(), "1", position);

            } else {
                for (int i = 0; i < mData.size(); i++) {

                    if (mData.get(i).get("group").equals(s)) {
                        mData.get(i).put("show", false);
                    }
                }
                mData.get(position).put("show", false);
            }

           /* if (Boolean.parseBoolean((String) mData.get(position).get("show"))){
            }else{
            }
            s = map.get("title").toString();

            if (map.get("info").equals("连接成功")) {
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).get("group").equals(s)) {
                        mData.get(i).put("show", false);
                    }
                }
                mData.get(position).put("info", "再次连接");
            } else {
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).get("group").equals(s)) {
                        mData.get(i).put("show", true);
                    }
                }
                mData.get(position).put("info", "连接成功");
            }*/
            recycleAdapter.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(getActivity(), MonitorActivity.class);
            Map<String, Object> map1 = mData.get(position);
            SerializableMap tmpmap = new SerializableMap();
            Bundle bundle = new Bundle();
            tmpmap.setMap(map1);
            bundle.putSerializable("monitorinfo", tmpmap);
            intent.putExtras(bundle);
            getActivity().startActivity(intent);
            //不同的视频画面跳转
        }


    }

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
                json = ControlUtils.chuangeStr(json);
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
                mData.get(num).put("show", true);
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
                String string =resultBean.getJsonString();
                string=ControlUtils.chuangeStr(string);
                MonitorGroupListBean monitorBean = ControlUtils.getEntityFromJson(string, MonitorGroupListBean.class);
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mData = getDatas();
    }

    private void setData(List<Map<String, Object>> list) {

        recycleAdapter = new HomeAdapter(getActivity(), list, groupkey);
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
        recycleAdapter.setOnItemLongClickListener(this);
    }


    @Override
    public void onItemLongClick(View view, final int position) {
        if (mData.get(position).get("group").equals("")) {
            alert = null;
            builder = new AlertDialog.Builder(getActivity());
            alert = builder
                    .setMessage("是否删除设备?")
                       /* .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MonitorActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })*/
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            delGroup( mData.get(position).get("id").toString(),String.valueOf(ControlUtils.userBean.getId()));
                            mData.remove(position);
                            recycleAdapter.notifyDataSetChanged();


//                            Toast.makeText(getActivity(), "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                        }
                    }).create();             //创建AlertDialog对象
            alert.show();


        }

    }

    public void delGroup(String groupid, String userid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("groupId", groupid);
        map.put("userId", userid);
        ControlUtils.getsEveryTime(HConstants.URL.AppMonitorGroupDeleteOnly, map, ResultBean.class, new ControlUtils.OnControlUtils<ResultBean>() {
            @Override
            public void onSuccess(String url, ResultBean resultBean, String result) {
                Log.d(TAG, "AAAAonSuccess: "+result);
                String string =resultBean.getJsonString();
                string=ControlUtils.chuangeStr(string);
            }

            @Override
            public void onFailure(String url) {
                Log.d(TAG, "onSuccess: "+url);
            }
        });

    }
}
