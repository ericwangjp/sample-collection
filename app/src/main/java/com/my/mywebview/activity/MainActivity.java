package com.my.mywebview.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.adapter.ClipViewPagerAdapter;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.fragment.ConvenienceFragment;
import com.my.mywebview.fragment.LifeFragment;
import com.my.mywebview.fragment.NewsFragment;
import com.my.mywebview.fragment.SportsFragment;
import com.my.mywebview.jpush.JPushUtil;
import com.my.mywebview.utils.log.LogManager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CommonBaseActivity implements
        ConvenienceFragment.OnFragmentInteractionListener,
        LifeFragment.OnFragmentInteractionListener,
        NewsFragment.OnFragmentInteractionListener,
        SportsFragment.OnFragmentInteractionListener {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    public static boolean isForeground = false;
    private ConvenienceFragment convenienceFragment;
    private LifeFragment lifeFragment;
    private NewsFragment newsFragment;
    private SportsFragment sportsFragment;
    private List<Fragment> lists;
    private ClipViewPagerAdapter clipViewPagerAdapter;
    private List<String>bottomTitles;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initJPush();
    }

    private void initJPush() {
        String udid = JPushUtil.getImei(getApplicationContext(), "");
        if (null != udid)
            LogManager.i("IMEI: " + udid);

        String appKey = JPushUtil.getAppKey(getApplicationContext());
        if (null == appKey) appKey = "AppKey异常";
        LogManager.i("AppKey: " + appKey);

        LogManager.i("RegId:");

        String packageName = getPackageName();
        LogManager.i("PackageName: " + packageName);

        String deviceId = JPushUtil.getDeviceId(getApplicationContext());
        LogManager.i("deviceId:" + deviceId);

        String versionName = JPushUtil.GetVersion(getApplicationContext());
        LogManager.i("Version: " + versionName);
        registerMessageReceiver();  // used for receive msg
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    private void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!JPushUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                setCostomMsg(showMsg.toString());
            }
        }
    }

    private void setCostomMsg(String msg) {
        //		 if (null != msgText) {
        //			 msgText.setText(msg);
        //			 msgText.setVisibility(android.view.View.VISIBLE);
        //         }
        LogManager.d("接收到我的自定义消息", msg);
        //		if(msg.contains("love") ){
        //			paras.putString("url", "http://www.2345.com");
        //			JumpUtils.invokeActivity(mContext, MyVideoActivity.class,"",paras);
        //		}
    }

    @Override
    protected void initData() {
        super.initData();
        lists = new ArrayList<>();
        if (newsFragment == null) {
            newsFragment = new NewsFragment();
        }
        lists.add(newsFragment);
        if (sportsFragment == null) {
            sportsFragment = new SportsFragment();
        }
        lists.add(sportsFragment);
        if (lifeFragment == null) {
            lifeFragment = new LifeFragment();
        }
        lists.add(lifeFragment);
        if (convenienceFragment == null) {
            convenienceFragment = new ConvenienceFragment();
        }
        lists.add(convenienceFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        clipViewPagerAdapter = new ClipViewPagerAdapter(fragmentManager, this, lists);
        viewPager.setAdapter(clipViewPagerAdapter);
        bottomTitles= Arrays.asList(new String[]{"新闻","体育","生活","便民"});
        initMagicIndicator();
    }

    private void initMagicIndicator() {
//        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator1);
        magicIndicator.setBackgroundColor(Color.BLACK);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return bottomTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);

                // load custom layout
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                final ImageView titleImg = (ImageView) customLayout.findViewById(R.id.title_img);
                final TextView titleText = (TextView) customLayout.findViewById(R.id.title_text);
                int[]pics={R.drawable.selector_bottom_img1,R.drawable.selector_bottom_img2,
                        R.drawable.selector_bottom_img3,R.drawable.selector_bottom_img4};
                titleImg.setBackgroundResource(pics[index]);
                titleText.setText(bottomTitles.get(index));
                customLayout.setBackgroundColor(Color.WHITE);
                commonPagerTitleView.setContentView(customLayout);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(getResources().getColor(R.color.color06C1AE));
                        titleImg.setSelected(true);
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Color.BLACK);
                        titleImg.setSelected(false);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                        titleImg.setScaleX(1.3f + (0.8f - 1.3f) * leavePercent);
                        titleImg.setScaleY(1.3f + (0.8f - 1.3f) * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        titleImg.setScaleX(0.8f + (1.3f - 0.8f) * enterPercent);
                        titleImg.setScaleY(0.8f + (1.3f - 0.8f) * enterPercent);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    /**
     * 在按下返回键时，不是退出当前APP而是进入后台运行，类似QQ支付宝效果
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
