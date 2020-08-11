package com.example.imooc_business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.imooc_business.model.CHANNEL;
import com.example.lib_common_ui.page_indicator.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

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
        initMagicIndicator();
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
}