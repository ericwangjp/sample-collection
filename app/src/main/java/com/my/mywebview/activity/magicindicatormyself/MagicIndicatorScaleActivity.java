package com.my.mywebview.activity.magicindicatormyself;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.fragment.ConvenienceFragment;
import com.my.mywebview.fragment.LifeFragment;
import com.my.mywebview.fragment.NewsFragment;
import com.my.mywebview.fragment.SportsFragment;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ScaleTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * magicIndicator可缩放平滑滚动的页面指示器
 * magicIndicator+fragment实现，无viewpager(ScaleTransitionPagerTitleView源码有改动，非GitHub源码)
 */
public class MagicIndicatorScaleActivity extends CommonBaseActivity implements
        NewsFragment.OnFragmentInteractionListener,
        ConvenienceFragment.OnFragmentInteractionListener,
        LifeFragment.OnFragmentInteractionListener,
        SportsFragment.OnFragmentInteractionListener {
    private List<String> mTitleDataList = new ArrayList<String>();
    private NewsFragment newsFragment;
    private LifeFragment lifeFragment;
    private ConvenienceFragment convenienceFragment;
    private SportsFragment sportsFragment;

    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.flayout_root)
    FrameLayout flayoutRoot;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_magic_indicator_scale);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mTitleDataList.add("新闻");
        mTitleDataList.add("体育");
        mTitleDataList.add("生活");
        mTitleDataList.add("便民");
        if(newsFragment==null){
            newsFragment=new NewsFragment();
        }
        if(lifeFragment==null){
            lifeFragment=new LifeFragment();
        }
        if(convenienceFragment==null){
            convenienceFragment=new ConvenienceFragment();
        }
        if(sportsFragment==null){
            sportsFragment=new SportsFragment();
        }
        initMagicScaleIndicator();
        // 设置默认的Fragment
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        newsFragment = new NewsFragment();
        transaction.replace(R.id.flayout_root, newsFragment);
        transaction.commit();
    }

    private void initMagicScaleIndicator() {
         final MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
         CommonNavigator commonNavigator = new CommonNavigator(this);
//        commonNavigator.setEnablePivotScroll(true);//多指示器模式，可以滑动
        commonNavigator.setAdjustMode(true);  // 自适应模式
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(magicIndicator);
                        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
                        fragmentContainerHelper.setDuration(300);
                        fragmentContainerHelper.handlePageSelected(index);
//                        mPager.setCurrentItem(index);
                        FragmentManager fm = getSupportFragmentManager();
                        // 开启Fragment事务
                        FragmentTransaction transaction = fm.beginTransaction();
                        switch (index){
                            case 0:
                                if(newsFragment==null){
                                    newsFragment=new NewsFragment();
                                }
                                // 使用当前Fragment的布局替代id_content的控件
                                transaction.replace(R.id.flayout_root, newsFragment);
                                break;
                            case 1:
                                if(sportsFragment==null){
                                    sportsFragment=new SportsFragment();
                                }
                                // 使用当前Fragment的布局替代id_content的控件
                                transaction.replace(R.id.flayout_root, sportsFragment);
                                break;
                            case 2:
                                if(lifeFragment==null){
                                    lifeFragment=new LifeFragment();
                                }
                                // 使用当前Fragment的布局替代id_content的控件
                                transaction.replace(R.id.flayout_root, lifeFragment);
                                break;
                            case 3:
                                if(convenienceFragment==null){
                                    convenienceFragment=new ConvenienceFragment();
                                }
                                // 使用当前Fragment的布局替代id_content的控件
                                transaction.replace(R.id.flayout_root, convenienceFragment);
                                break;

                        }
                        // 事务提交
                        transaction.commit();
                    }
                });
                return simplePagerTitleView;
            }
            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(R.color.color1CABEB,R.color.colorEC4949,R.color.color2DC868,R.color.colorDB4E43);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
//        final FragmentContainerHelper fragmentContainerHelper = new FragmentContainerHelper(magicIndicator);
//        fragmentContainerHelper.setInterpolator(new OvershootInterpolator(2.0f));
//        fragmentContainerHelper.setDuration(300);
//        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                fragmentContainerHelper.handlePageSelected(position);
//            }
//        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
