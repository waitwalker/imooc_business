package com.example.imooc_business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.imooc_business.model.CHANNEL;

import net.lucode.hackware.magicindicator.MagicIndicator;

public class HomeActivity extends FragmentActivity {

    // 指定首页要出现的卡片
    private static final CHANNEL[] CHANNELS = new CHANNEL[]{
            CHANNEL.MY, CHANNEL.DISCORY, CHANNEL.FRIEND
    };

    // view
    private DrawerLayout mDrawerLayout;
    private View mToggleView;
    private View mSearchView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        transferredParameter();

        initView();
        initData();

    }

    private void initData() {}

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggleView = findViewById(R.id.toggle_view);
        mSearchView = findViewById(R.id.search_view);
        mViewPager = findViewById(R.id.view_pager);
    }

    private void transferredParameter() {
        Intent intent = getIntent();
        if (intent != null) {
            String string = intent.getStringExtra("name");
            Log.d("1","正向传过来的参数:" + string);
        }
    }

    private void back() {
        Intent intent = new Intent();
        intent.putExtra("value", "Home");
        setResult(1234,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }
}