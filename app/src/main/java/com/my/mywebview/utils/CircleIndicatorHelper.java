package com.my.mywebview.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by WJP on 2017/4/10.
 */

public class CircleIndicatorHelper {
    private CirclePageIndicator mCircleIndicator;
    private Context mContext;

    public CircleIndicatorHelper(Context context) {
        mCircleIndicator = new CirclePageIndicator(context);
        mContext = context;
    }

    public void setViewpager(ViewPager viewPager) {
        ViewGroup parentView = (ViewGroup) viewPager.getParent();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, dpToPx(8));
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mCircleIndicator.setLayoutParams(lp);
        parentView.addView(mCircleIndicator);
        mCircleIndicator.setViewPager(viewPager);
    }

    public void setFillColor(String colorString) {
        int color = Color.parseColor(colorString);
        mCircleIndicator.setFillColor(color);
    }

    public void setDefaultColor(String colorString) {
        int color = Color.parseColor(colorString);
        mCircleIndicator.setDefaultColor(color);
    }

    public void setRadius(int radius) {
        mCircleIndicator.setRadius(dpToPx(radius));
    }

    private int dpToPx(int dp) {
        DisplayMetrics resourec = mContext.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resourec);
    }
}
