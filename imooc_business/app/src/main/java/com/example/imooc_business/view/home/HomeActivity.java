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
import com.example.imooc_business.model.login.LoginEvent;
import com.example.imooc_business.view.home.adapter.HomeAdapter;
import com.example.imooc_business.view.login.LoginActivity;
import com.example.lib_audio.mediaplayer.core.AudioController;
import com.example.lib_audio.mediaplayer.model.AudioBean;
import com.example.lib_common_ui.base.BaseActivity;
import com.example.lib_common_ui.page_indicator.ScaleTransitionPagerTitleView;
import com.example.lib_image_loader.app.ImageLoaderManager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

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

    private ArrayList<AudioBean> mLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        transferredParameter();
        EventBus.getDefault().register(this);
        initView();
        initData();

    }

    private void initData() {
        mLists.add(
                new AudioBean("100002", "http://sq-sycdn.kuwo.cn/resource/n1/98/51/3777061809.mp3", "勇气",
                        "梁静茹", "勇气", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698193627&di=711751f16fefddbf4cbf71da7d8e6d66&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D213168965%2C1040740194%26fm%3D214%26gp%3D0.jpg",
                        "4:40"));
        mLists.add(new AudioBean("100001", "http://sp-sycdn.kuwo.cn/resource/n2/85/58/433900159.mp3",
                "以你的名字喊我", "周杰伦", "七里香", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698076304&di=e6e99aa943b72ef57b97f0be3e0d2446&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fblog%2F201401%2F04%2F20140104170315_XdG38.jpeg",
                "4:30"));
        mLists.add(
                new AudioBean("100003", "http://sp-sycdn.kuwo.cn/resource/n2/52/80/2933081485.mp3", "灿烂如你",
                        "汪峰", "春天里", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698239736&di=3433a1d95c589e31a36dd7b4c176d13a&imgtype=0&src=http%3A%2F%2Fpic.zdface.com%2Fupload%2F201051814737725.jpg",
                        "3:20"));
        mLists.add(
                new AudioBean("100004", "http://sr-sycdn.kuwo.cn/resource/n2/33/25/2629654819.mp3", "小情歌",
                        "五月天", "小幸运", "电影《不能说的秘密》主题曲,尤其以最美的不是下雨天,是与你一起躲过雨的屋檐最为经典",
                        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559698289780&di=5146d48002250bf38acfb4c9b4bb6e4e&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fp%2F20131220%2Fbki-20131220170401-1254350944.jpg",
                        "2:45"));
        AudioController.getInstance().setQueue(mLists);
    }

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        unLogginLayout.setVisibility(View.GONE);
        mPhotoView.setVisibility(View.VISIBLE);
        ImageLoaderManager.getInstance().displayImageForCircle(mPhotoView, UserManager.getInstance().getUser().data.photoUrl);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}