package com.my.mywebview.application;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by WJP on 2017/3/15.
 */

public class ApplicationControl extends Application{
    private static final String TAG = "JPush";

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
