package com.example.lib_common_ui.page_indicator;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {

    private float mMinScale = 0.9f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);
        setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
        setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);
        setScaleX(mMinScale + (1.0f - mMinScale) * leavePercent);
        setScaleY(mMinScale + (1.0f - mMinScale) * leavePercent);
    }

    public float getmMinScale() {
        return mMinScale;
    }

    public void setmMinScale(float mMinScale) {
        this.mMinScale = mMinScale;
    }
}
