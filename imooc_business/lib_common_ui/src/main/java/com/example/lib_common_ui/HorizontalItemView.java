package com.example.lib_common_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HorizontalItemView extends RelativeLayout {

    private Context mContext;

    // 自定义属性
    private int mPaddingRight;
    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingBottom;

    private int mIconWidth;
    private int mIconHeight;
    private Drawable mIcon;
    private int mIconPaddingRight;

    private float mTileTextSize;
    private int mTileTextColor;
    private String mTileText;

    private int mTipTextSize;
    private int mTipTextColor;
    private String mTipText;
    private int mTipPaddingLeft;
    private boolean mTipVisibility;

    private float mRightTextSize;
    private int mRightTextColor;
    private String mRightText;

    private Drawable mRightIcon;
    private int mRightIconWidth;
    private int mRightIconHeight;
    private int mRightIconMarginLeft;

    // 自定义view
    private ImageView mTitleView;
    private TextView mTileView;
    private TextView mTipView;
    private TextView mRightView;
    private ImageView mRightImageView;



    public HorizontalItemView(Context context) {
        this(context, null);
    }

    public HorizontalItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalItemView(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        mContext = context;
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.HorizontalItemView);
        mPaddingLeft = array.getLayoutDimension(R.styleable.HorizontalItemView_paddingLeft, 20);
        mPaddingRight = array.getLayoutDimension(R.styleable.HorizontalItemView_paddingRight, 20);
        mPaddingTop = array.getLayoutDimension(R.styleable.HorizontalItemView_paddingTop, 10);
        mPaddingBottom = array.getLayoutDimension(R.styleable.HorizontalItemView_paddingBottom, 10);

        mIconWidth = array.getLayoutDimension(R.styleable.HorizontalItemView_hIconWidth, 70);
        mIconHeight = array.getLayoutDimension(R.styleable.HorizontalItemView_hIconHeight, 70);
        mIcon = array.getDrawable(R.styleable.HorizontalItemView_hIcon);
        mIconPaddingRight = array.getLayoutDimension(R.styleable.HorizontalItemView_iconPaddingRight, 15);

        mTileTextSize = array.getDimension(R.styleable.HorizontalItemView_tileTextSize,15);
        mTileTextColor = array.getColor(R.styleable.HorizontalItemView_tileTextColor,0xff333333);
        mTipText = array.getString(R.styleable.HorizontalItemView_tileText);

        mTipTextSize = array.getLayoutDimension(R.styleable.HorizontalItemView_hTipTextSize,15);
        mTipTextColor = array.getColor(R.styleable.HorizontalItemView_hTipTextColor,0xff333333);
        mTipText = array.getString(R.styleable.HorizontalItemView_hTipText);
        mTipVisibility = array.getBoolean(R.styleable.HorizontalItemView_hTipVisiblity,false);
        mTipPaddingLeft = array.getLayoutDimension(R.styleable.HorizontalItemView_hTipPaddingLeft, 2);

        mRightIcon = array.getDrawable(R.styleable.HorizontalItemView_rightIcon);
        mRightIconWidth = array.getLayoutDimension(R.styleable.HorizontalItemView_rightIconWidth,20);
        mRightIconHeight = array.getLayoutDimension(R.styleable.HorizontalItemView_rightIconHeight,30);
        mRightIconMarginLeft = array.getLayoutDimension(R.styleable.HorizontalItemView_rightIconMarginLeft,20);

        mRightTextSize = array.getDimension(R.styleable.HorizontalItemView_rightTextSize,12);
        mRightTextColor = array.getColor(R.styleable.HorizontalItemView_rightTextColor,0xff666666);
        mRightText = array.getString(R.styleable.HorizontalItemView_rightText);
        array.recycle();
        createView();
    }

    // 初始化view
    private void createView() {
        RelativeLayout rootLayout = new RelativeLayout(mContext);

        RelativeLayout layout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);

        // 最左边icon
        mTitleView = new ImageView(mContext);
        mTitleView.setId(R.id.hornized_image_id);
        mTitleView.setScaleType(ImageView.ScaleType.FIT_XY);
        mTitleView.setImageDrawable(mIcon);
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(mIconWidth, mIconHeight);
        titleParams.setMargins(0,0, mIconPaddingRight,0);
        titleParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(mTileView,titleParams);

        mTileView = new TextView(mContext);
        mTileView.setId(R.id.hornized_tile_id);
        mTileView.setText(mTileText);
        mTileView.setTextColor(mTileTextColor);
        mTileView.setTextSize(mTileTextSize);
        RelativeLayout.LayoutParams tileParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        tileParams.addRule(RelativeLayout.RIGHT_OF, R.id.hornized_image_id);
        tileParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layout.addView(mTileView, tileParams);

        if (mTipVisibility) {
            mTipView = new TextView(mContext);
            mTipView.setText(mTipText);
            mTipView.getPaint().setTextSize(mTipTextSize);
            mTipView.setTextColor(mTipTextColor);
            RelativeLayout.LayoutParams tipParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            tipParams.addRule(RelativeLayout.RIGHT_OF, R.id.hornized_tile_id);
            tipParams.addRule(RelativeLayout.CENTER_VERTICAL);
            tipParams.setMargins(mTipPaddingLeft,0,0,0);
            layout.addView(mTipView, tipParams);
        }

        mRightView = new TextView(mContext);
        mRightView.setText(mRightText);
        mRightView.setTextColor(mRightTextColor);
        mRightView.setTextSize(mRightTextSize);
        RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (mRightIcon != null) {
            mRightImageView = new ImageView(mContext);
            mRightImageView.setId(R.id.hornized_right_image_id);
            mRightImageView.setImageDrawable(mRightIcon);
            RelativeLayout.LayoutParams rightImageParams = new RelativeLayout.LayoutParams(mRightIconWidth, mRightIconHeight);
            rightImageParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightImageParams.addRule(RelativeLayout.CENTER_VERTICAL);
            rightImageParams.setMargins(mRightIconMarginLeft,0,0,0);
            layout.addView(mRightImageView, rightImageParams);

            rightParams.addRule(RelativeLayout.LEFT_OF,R.id.hornized_right_image_id);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
            rightParams.setMargins(mRightIconMarginLeft,0,0,0);
            layout.addView(mRightView, rightParams);
        } else {
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layout.addView(mRightView,rightParams);
        }

        rootLayout.addView(layout, layoutParams);
        addView(rootLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
