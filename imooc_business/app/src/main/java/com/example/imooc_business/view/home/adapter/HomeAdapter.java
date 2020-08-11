package com.example.imooc_business.view.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.imooc_business.model.CHANNEL;

public class HomeAdapter extends FragmentPagerAdapter {

    private CHANNEL[] mList;

    public HomeAdapter(@NonNull FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        mList = datas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type){
            case CHANNEL.MINE_ID:
                break;
            case CHANNEL.DISCORY_ID:
                break;
            case CHANNEL.FRIEND_ID:
                break;
            case CHANNEL.VIDEO_ID:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
