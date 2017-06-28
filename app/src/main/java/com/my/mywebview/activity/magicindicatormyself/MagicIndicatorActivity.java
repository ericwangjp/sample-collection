package com.my.mywebview.activity.magicindicatormyself;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.adapter.MyPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 该页面效果实现为MagicIndicator+ViewPager
 */
public class MagicIndicatorActivity extends CommonBaseActivity {

    private List<String> mTitleDataList = new ArrayList<String>();
    private List<View> pageViews = new ArrayList<View>();
    private MyPagerAdapter pagerAdapter;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_magic_indicator);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mTitleDataList.add("新闻");
        mTitleDataList.add("体育");
        mTitleDataList.add("国内");
        mTitleDataList.add("国际");
        mTitleDataList.add("政务");
        mTitleDataList.add("便民");
        mTitleDataList.add("社会");
        mTitleDataList.add("服务");
        mTitleDataList.add("交通");
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot = new LinearLayout(this);
        layoutRoot.setLayoutParams(layoutParamsRoot);
        layoutRoot.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView.topMargin = 20;
        layoutParamsTextView.bottomMargin = 20;
        layoutParamsTextView.leftMargin = 20;
        layoutParamsTextView.rightMargin = 20;
        layoutParamsTextView.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo = new TextView(this);
        textInfo.setGravity(Gravity.CENTER);
        textInfo.setTextSize(18);
        layoutRoot.addView(textInfo, layoutParamsTextView);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot1=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot1.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot1 = new LinearLayout(this);
        layoutRoot1.setLayoutParams(layoutParamsRoot1);
        layoutRoot1.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView1.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo1 = new TextView(this);
        textInfo1.setGravity(Gravity.CENTER);
        textInfo1.setTextSize(18);
        layoutRoot1.addView(textInfo1, layoutParamsTextView1);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot2.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot2 = new LinearLayout(this);
        layoutRoot2.setLayoutParams(layoutParamsRoot2);
        layoutRoot2.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView2.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo2 = new TextView(this);
        textInfo2.setGravity(Gravity.CENTER);
        textInfo2.setTextSize(18);
        layoutRoot2.addView(textInfo2, layoutParamsTextView2);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot3=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot3.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot3 = new LinearLayout(this);
        layoutRoot3.setLayoutParams(layoutParamsRoot3);
        layoutRoot3.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView3.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo3 = new TextView(this);
        textInfo3.setGravity(Gravity.CENTER);
        textInfo3.setTextSize(18);
        layoutRoot3.addView(textInfo3, layoutParamsTextView3);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot4=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot4.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot4 = new LinearLayout(this);
        layoutRoot4.setLayoutParams(layoutParamsRoot4);
        layoutRoot4.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView4.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo4 = new TextView(this);
        textInfo4.setGravity(Gravity.CENTER);
        textInfo4.setTextSize(18);
        layoutRoot4.addView(textInfo4, layoutParamsTextView4);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot5=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot5.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot5 = new LinearLayout(this);
        layoutRoot5.setLayoutParams(layoutParamsRoot5);
        layoutRoot5.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView5.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo5 = new TextView(this);
        textInfo5.setGravity(Gravity.CENTER);
        textInfo5.setTextSize(18);
        layoutRoot5.addView(textInfo5, layoutParamsTextView5);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot6=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot6.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot6 = new LinearLayout(this);
        layoutRoot6.setLayoutParams(layoutParamsRoot6);
        layoutRoot6.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView6.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo6 = new TextView(this);
        textInfo6.setGravity(Gravity.CENTER);
        textInfo6.setTextSize(18);
        layoutRoot6.addView(textInfo6, layoutParamsTextView6);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot7=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot7.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot7 = new LinearLayout(this);
        layoutRoot7.setLayoutParams(layoutParamsRoot7);
        layoutRoot7.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView7 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView7.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo7 = new TextView(this);
        textInfo7.setGravity(Gravity.CENTER);
        textInfo7.setTextSize(18);
        layoutRoot7.addView(textInfo7, layoutParamsTextView7);
//        *********************************代码布局结束****************************
        //******************************代码布局开始********************
        //根布局参数
        LinearLayout.LayoutParams layoutParamsRoot8=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsRoot8.gravity= Gravity.CENTER;
        //根布局
        LinearLayout layoutRoot8 = new LinearLayout(this);
        layoutRoot8.setLayoutParams(layoutParamsRoot8);
        layoutRoot8.setOrientation(LinearLayout.VERTICAL);
        //textView布局参数
        LinearLayout.LayoutParams layoutParamsTextView8 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParamsTextView8.gravity=Gravity.CENTER;
        //初始化textView
        TextView textInfo8 = new TextView(this);
        textInfo8.setGravity(Gravity.CENTER);
        textInfo8.setTextSize(18);
        layoutRoot8.addView(textInfo8, layoutParamsTextView8);
//        *********************************代码布局结束****************************
            textInfo.setText("我是第1个页面");
            textInfo1.setText("我是第2个页面");
            textInfo2.setText("我是第3个页面");
            textInfo3.setText("我是第4个页面");
            textInfo4.setText("我是第5个页面");
            textInfo5.setText("我是第6个页面");
            textInfo6.setText("我是第7个页面");
            textInfo7.setText("我是第8个页面");
            textInfo8.setText("我是第9个页面");
            pageViews.add(layoutRoot);
            pageViews.add(layoutRoot1);
            pageViews.add(layoutRoot2);
            pageViews.add(layoutRoot3);
            pageViews.add(layoutRoot4);
            pageViews.add(layoutRoot5);
            pageViews.add(layoutRoot6);
            pageViews.add(layoutRoot7);
            pageViews.add(layoutRoot8);
        pagerAdapter=new MyPagerAdapter(this,pageViews);
        viewPager.setAdapter(pagerAdapter);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setEnablePivotScroll(true);//多指示器模式，可以滑动
//        commonNavigator.setAdjustMode(true);  // 自适应模式
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setTextSize(18);
                colorTransitionPagerTitleView.setBackgroundColor(getResources().getColor(R.color.colorFFB48C));
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(mTitleDataList.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
    }

    @Override
    protected void onListener() {
        super.onListener();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }
}
