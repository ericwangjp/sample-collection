package com.my.mywebview.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by WJP on 2017/3/15.
 */

public class ApplicationControl extends Application{
    private static final String TAG = "JPush";
    public static Context appContext;
    public RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
//        LeakCanary.install(this);
        refWatcher = LeakCanary.install(this);
        // Normal app init code...
        appContext=getApplicationContext();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    /**
     * 获取APPcontext
     * @return
     */
    public static Context getAppContext(){
        return appContext;
    }

    /**
     * 内存检测观察者
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        ApplicationControl application = (ApplicationControl) context
                .getApplicationContext();
        return application.refWatcher;
    }
}
