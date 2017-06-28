package com.my.mywebview.activity;

import android.os.Bundle;
import android.os.Message;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.LaunchModeUtils;

public class SplashActivity extends CommonBaseActivity {
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /** 打开标记 是否更新和首次启动 */
        LaunchModeUtils.getInstance().markOpenApp(mContext);
    }

    @Override
    protected void initData() {
        super.initData();
        handler.sendEmptyMessageDelayed(0,2000);
//            handler.sendEmptyMessage(0);
    }

    @Override
    protected void handler(Message msg) {
        super.handler(msg);
        if(msg.what==0){
//            goNextPage();
            JumpUtils.goNext(mContext, GuideActivity.class, "", paras, true);
        }
    }

    private void goNextPage() {
        // 页面跳转
        Class<?> nextClass = getNextClass();
        JumpUtils.goNext(mContext, nextClass, "", paras, true);
        //设置跳转动画
        overridePendingTransition(R.anim.intro_alph_in,R.anim.intro_alph_out);
    }

    public Class<?> getNextClass() {
        // 如果是首次启动，则进入引导页
        if (LaunchModeUtils.getInstance().isFirstOpen()) {
            return GuideActivity.class;
        }
        // 设置了手势密码则进入手势密码验证
//        if (ApplicationController.userInfo.gestureInfo.gestrueOpen) {
//            paras.putString("from", SplashActivity.class.getSimpleName());
//            return GestureActivity.class;
//        }
//        如果不是首次启动，则进入主页
        return MainActivity.class;
    }
}
