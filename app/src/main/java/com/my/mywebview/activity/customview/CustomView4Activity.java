package com.my.mywebview.activity.customview;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.utils.log.LogManager;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 利用drawerlayout快速实现抽屉式菜单，sliding menu
 * v4包下的功能
 */
public class CustomView4Activity extends CommonBaseActivity implements View.OnClickListener{
    @BindView(R.id.tv_main_content)
    TextView tvMainContent;
    @BindView(R.id.tv_layer_1)
    TextView tvLayer1;
    @BindView(R.id.tv_layer_2)
    TextView tvLayer2;
    @BindView(R.id.tv_layer_3)
    TextView tvLayer3;
    @BindView(R.id.tv_layer_4)
    TextView tvLayer4;
    @BindView(R.id.tv_layer_5)
    TextView tvLayer5;
    @BindView(R.id.ll_drawer)
    LinearLayout llDrawer;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private TitleHelper title;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_custom_view4);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        tvMainContent.setOnClickListener(this);
        tvLayer1.setOnClickListener(this);
        tvLayer2.setOnClickListener(this);
        tvLayer3.setOnClickListener(this);
        tvLayer4.setOnClickListener(this);
        tvLayer5.setOnClickListener(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
//        title = new TitleHelper(this);
//        title.setPicLeft(R.mipmap.ic_back);
//        title.setTextLeft(R.string.common_back);
//        title.setTextCenter(R.string.title_drawerlayout);
//        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }


    @Override
    protected void onListener() {
        super.onListener();
        drawerlayout.setDrawerTitle(Gravity.CENTER,"我是侧滑标题头");
//        设置DrawerListener的子类SimpleDrawerListener，使用这个类的时候不必实现全部的回调函数，可以根据自己的需要重写相应的方法
//        mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener()

        drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            /**
             * 当抽屉被滑动的时候调用此方法
             * slideOffset 表示 滑动的幅度（0-1）
             */
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                LogManager.i("侧滑菜单滑动距离：",slideOffset);
            }

            /**
             * 当一个抽屉被完全打开的时候被调用
             */
            @Override
            public void onDrawerOpened(View drawerView) {
                LogManager.i("侧滑菜单滑动状态：","侧滑完全打开");
            }

            /**
             * 当一个抽屉完全关闭的时候调用此方法
             */
            @Override
            public void onDrawerClosed(View drawerView) {
                LogManager.i("侧滑菜单滑动状态：","侧滑完全关闭");
            }

            /**
             * 当抽屉滑动状态改变的时候被调用
             * 状态值是STATE_IDLE（闲置--0）, STATE_DRAGGING（拖拽的--1）, STATE_SETTLING（固定--2）中之一。
             * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
             */
            @Override
            public void onDrawerStateChanged(int newState) {
                LogManager.i("侧滑菜单滑动状态：" , newState);
            }
        });

        //        使用DrawerListener的子类ActionBarDrawerToggle。一般与ActionBar结合使用
//        mDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout,
//                R.drawable.ic_drawer, R.string.drawer_open,
//                R.string.drawer_close) {
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                getActionBar().setTitle("侧滑关闭");
//                invalidateOptionsMenu();
//            }
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle("侧滑打开");
//                invalidateOptionsMenu();
//            }
//        };
//        drawerlayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tv_main_content:
                ToastUtils.show(this,"点击了内容！");
                break;
            case R.id.tv_layer_1:
                ToastUtils.show(this,"点击了菜单1！");
                break;
            case R.id.tv_layer_2:
                ToastUtils.show(this,"点击了菜单2！");
                break;
            case R.id.tv_layer_3:
                ToastUtils.show(this,"点击了菜单3！");
                break;
            case R.id.tv_layer_4:
                ToastUtils.show(this,"点击了菜单4！");
                break;
            case R.id.tv_layer_5:
                ToastUtils.show(this,"点击了菜单5！");
                break;
        }
    }
}
