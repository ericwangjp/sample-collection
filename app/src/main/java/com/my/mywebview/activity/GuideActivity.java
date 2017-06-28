package com.my.mywebview.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.adapter.MyPagerAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.CircleIndicatorHelper;
import com.my.mywebview.utils.JumpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends CommonBaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_enter)
    Button btnEnter;
    @BindView(R.id.btn_jump)
    Button btnJump;
    private CircleIndicatorHelper mIndicatorHelper;
    private List<View> datas;
    private MyPagerAdapter myPagerAdapter;
    private Animation btnEnterAnimation;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        datas = new ArrayList<View>();
        int[] pics = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4,
                R.drawable.guide5, R.drawable.guide6, R.drawable.guide7};
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(pics[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            datas.add(imageView);
        }
        myPagerAdapter = new MyPagerAdapter(this, datas);
        viewPager.setAdapter(myPagerAdapter);
//      indicator实例一定要写在viewpagwer.setAdapter()之后
        mIndicatorHelper = new CircleIndicatorHelper(this);
        mIndicatorHelper.setViewpager(viewPager);
        mIndicatorHelper.setFillColor("#651717");
        mIndicatorHelper.setDefaultColor("#00A2E8");
        mIndicatorHelper.setRadius(5);
    }

    @Override
    protected void onListener() {
        super.onListener();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == datas.size() - 1) {
                    btnEnter.setVisibility(View.VISIBLE);
                } else {
                    btnEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == datas.size() - 1) {
                    btnEnter.setVisibility(View.VISIBLE);
                    btnEnterAnimation = AnimationUtils.loadAnimation(mContext,R.anim.anim_btn_click_enter);
                    btnEnter.startAnimation(btnEnterAnimation);
                } else {
                    btnEnter.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goNext(mContext, MainActivity.class, "", paras, true);
            }
        });
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.goNext(mContext, MainActivity.class, "", paras, true);
            }
        });
    }

}
