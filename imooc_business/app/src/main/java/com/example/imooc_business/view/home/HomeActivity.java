package com.example.imooc_business.view.home;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.imooc_business.R;
import com.example.imooc_business.manager.UserManager;
import com.example.imooc_business.model.CHANNEL;
import com.example.imooc_business.view.home.adapter.HomeAdapter;
import com.example.imooc_business.view.login.LoginActivity;
import com.example.lib_common_ui.base.BaseActivity;
import com.example.lib_common_ui.page_indicator.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

public class HomeActivity extends BaseActivity implements CompoundButton.OnClickListener {

    // 指定首页要出现的卡片
    private static final CHANNEL[] CHANNELS = new CHANNEL[]{
            CHANNEL.MY, CHANNEL.DISCORY, CHANNEL.FRIEND
    };

    // view
    private DrawerLayout mDrawerLayout;
    private View mToggleView;
    private View mSearchView;
    private ViewPager mViewPager;

    private HomeAdapter mAdapter;
    private View mDrawerQrcodeView;
    private View mDrawerShareView;
    private View unLogginLayout;
    private ImageView mPhotoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        transferredParameter();

        initView();
        initData();

    }

    private void initData() {}

    // 初始化view
    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setOnClickListener(this);
        mSearchView = findViewById(R.id.search_view);
        mViewPager = findViewById(R.id.view_pager);
        mAdapter = new HomeAdapter(getSupportFragmentManager(), CHANNELS);
        mViewPager.setAdapter(mAdapter);
        initMagicIndicator();

        mDrawerQrcodeView = findViewById(R.id.home_qrcode);
        mDrawerQrcodeView.setOnClickListener(this);
        mDrawerShareView = findViewById(R.id.home_music);
        mDrawerShareView.setOnClickListener(this);

        findViewById(R.id.online_music_view).setOnClickListener(this);
        findViewById(R.id.check_update_view).setOnClickListener(this);

        unLogginLayout = findViewById(R.id.unlogin_layout);
        unLogginLayout.setOnClickListener(this);
        mPhotoView = findViewById(R.id.avatar_view);
        findViewById(R.id.exit_layout).setOnClickListener(this);
    }

    // 初始化page 指示器 类似于联动效果的上部
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(Color.WHITE);

        // 为指示器创建一个navigator
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return CHANNELS == null? 0 : CHANNELS.length;
            }

            // 初始化title view 效果
            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(HomeActivity.this);
                simplePagerTitleView.setText(CHANNELS[index].getKey());
                simplePagerTitleView.setTextSize(19);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                // 正常颜色
                simplePagerTitleView.setNormalColor(Color.parseColor("#999999"));
                //选中颜色
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));

                // 设置标题的点击事件
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 1.0f;
            }
        });

        // 指示器设置中间的导航标题效果
        magicIndicator.setNavigator(commonNavigator);

        // 上下联动效果绑定
        ViewPagerHelper.bind(magicIndicator, mViewPager);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_layout:
                finish();
                System.exit(0);
                break;
            case R.id.unlogin_layout:
                if (!UserManager.getInstance().hasLogin()) {
                    navigateTo(this, LoginActivity.class);
                } else {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }
                Log.d("1","抽屉登录被点击");
                break;
            case R.id.toggle_view:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;
            case R.id.home_qrcode:
                Log.d("1","扫一扫被点击");
                break;
            case R.id.home_music:
                Log.d("1","home_music");
                break;
            case R.id.online_music_view:
                Log.d("1","online_music_view");
                break;
            case R.id.check_update_view:
                Log.d("1","检查更新");
                break;
        }
    }
}