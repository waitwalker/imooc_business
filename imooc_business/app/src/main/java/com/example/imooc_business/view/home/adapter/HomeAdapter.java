package com.example.imooc_business.view.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.imooc_business.model.CHANNEL;
import com.example.imooc_business.view.discory.DiscoryFragment;
import com.example.imooc_business.view.friend.FriendFragment;
import com.example.imooc_business.view.mine.MineFragment;
import com.example.imooc_business.view.video.VideoFragment;

// 首页ViewPager adapter
public class HomeAdapter extends FragmentPagerAdapter {

    private CHANNEL[] mList;

    public HomeAdapter(@NonNull FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        mList = datas;
    }

    // 初始化对应的Fragment  这种方法,滑动到哪页会创建哪个,优化内存
    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type){
            case CHANNEL.MINE_ID:
                return MineFragment.newInstance();
            case CHANNEL.DISCORY_ID:
                return DiscoryFragment.newInstance();
            case CHANNEL.FRIEND_ID:
                return FriendFragment.newInstance();
            case CHANNEL.VIDEO_ID:
                return VideoFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
