package com.yongxing.activity.demo01.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yongxing.activity.demo01.Bean.SpaceBean;
import com.yongxing.activity.demo01.R;
import com.yongxing.activity.demo01.fragment.NavigationFragment;
import com.yongxing.activity.demo01.fragment.RadioFragment;
import com.yongxing.activity.demo01.fragment.TabLayoutFragment;
import com.yongxing.activity.demo01.fragment.TabLayoutFragment2;
import com.yongxing.activity.demo01.fragment.TextTabFragment;
import com.yongxing.activity.demo01.service.HtmlService;
import com.yongxing.activity.demo01.service.HttpUtil;
import com.yongxing.activity.demo01.utils.NightModeHelper;
import com.yongxing.activity.demo01.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NavigationFragment mNavigationFragment;
    String TAG = "YMR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.blue);
        setCurrentFragment();
    }
    private void setCurrentFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mNavigationFragment = NavigationFragment.newInstance(getString(R.string.navigation_navigation_bar));
        transaction.replace(R.id.frame_content, mNavigationFragment).commit();
    }


    @Override
    public void onClick(View view) {

    }


  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings:
                Snackbar.make(mDrawerLayout, "Settings", Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                mNightModeHelper.toggle();
//                Configuration newConfig = new Configuration(getResources().getConfiguration());
//                newConfig.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
//                newConfig.uiMode |= uiNightMode;
//                getResources().updateConfiguration(newConfig, null);
//                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/



}

